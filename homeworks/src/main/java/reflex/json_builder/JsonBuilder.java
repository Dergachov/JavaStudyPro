package reflex.json_builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonBuilder {
    private ArrayList<ArrayList> schema;
    private ArrayList<ArrayList> navigationBranch;
    private ArrayList lastBranch;
    private final static int INDEX_BRANCH_NAME = 0;
    private final static int INDEX_DATA_TYPE = 1;
    private final static int INDEX_DATA = 2;
    private final static String TYPE_MULTIPLE_ARRAY = "[ARRAY::MULTIPLE]";
    private final static String TYPE_SIMPLE_ARRAY = "[ARRAY::SIMPLE]";
    private boolean groupModificationFlag;


    public JsonBuilder() {
        this.schema = new ArrayList<ArrayList>();
        this.navigationBranch = new ArrayList<ArrayList>();
        this.groupModificationFlag = false;
    }

    private LinkedHashMap<String, Object> createPair(String name, Object value) {
        LinkedHashMap<String, Object> newPair = new LinkedHashMap<String, Object>();
        newPair.put(name, value);
        return newPair;
    }

    private ArrayList<Object> createBranch(String newBranchName) {
        ArrayList<Object> branchPairBucket = new ArrayList<Object>();
        ArrayList<Object> branchMetaData = new ArrayList<Object>();
        branchMetaData.add(newBranchName);
        branchMetaData.add(null);
        branchMetaData.add(branchPairBucket);
        return branchMetaData;
    }

    private ArrayList searchBranch(String parentName) {
        if (!navigationBranch.isEmpty()) {
            for (int index = navigationBranch.size() - 1; index >= 0; index--) {
                ArrayList branch = navigationBranch.get(index);
                if (branch.get(INDEX_BRANCH_NAME) instanceof String) {
                    String branchName = (String) branch.get(INDEX_BRANCH_NAME);
                    if (branchName != null & parentName.equals(branchName)) {
                        return branch;
                    }
                }
            }
        }
        return null;
    }

    private ArrayList searchGroup(ArrayList list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Object value = list.get(i);
            if (value instanceof ArrayList) {
                return (ArrayList) value;
            }
        }
        return null;
    }

    public void addParentPair(String pairName, Object pairValue) {
        ArrayList<Object> pairBucket = new ArrayList<Object>();
        pairBucket.add(createPair(pairName, pairValue));
        ArrayList<Object> metaData = new ArrayList<Object>();
        metaData.add(null);
        metaData.add(null);
        metaData.add(pairBucket);
        navigationBranch.add(metaData); // save position in branch for navigation
        schema.add(metaData);
    }

    public void addPair(String parentBranch, String pairName, Object pairValue) {
        ArrayList<ArrayList> branchList = searchBranch(parentBranch);
        if (branchList != null) {
            branchList.get(INDEX_DATA).add(createPair(pairName, pairValue));
        }
    }

    public void addBranch(String newBranchName) {
        ArrayList<Object> newBranch = createBranch(newBranchName);
        schema.add(newBranch);
        navigationBranch.add(newBranch);
    }

    public void addBranch(String parentName, String newBranchName) {
        ArrayList branchList = searchBranch(parentName);
        ArrayList<Object> newBranch = createBranch(newBranchName);
        if (branchList != null) {
            ArrayList data = (ArrayList) branchList.get(INDEX_DATA);
            data.add(newBranch);
            navigationBranch.add(newBranch);
        } else {
            navigationBranch.add(newBranch);
            ArrayList<Object> metaData = new ArrayList<Object>();
            metaData.add(parentName);
            metaData.add(null);
            metaData.add(newBranch);
            navigationBranch.add(metaData); // save position in branch for navigation
            schema.add(metaData);
        }
    }

    private ArrayList createMultiArrayBranch(String branchName) {
        ArrayList<Object> branchBucket = new ArrayList<Object>();
        ArrayList<Object> branchMetaData = new ArrayList<Object>();
        branchMetaData.add(branchName);
        branchMetaData.add(TYPE_MULTIPLE_ARRAY);
        branchMetaData.add(branchBucket);
        return branchMetaData;
    }

    public void addMultiArrayBranch(String parentArrayBranchName) {
        ArrayList newBranch = createMultiArrayBranch(parentArrayBranchName);
        schema.add(newBranch);
        navigationBranch.add(newBranch);
    }

    public void addMultiArrayBranch(String parentBranchName, String arrayBranchName) {
        ArrayList<ArrayList> branchList = searchBranch(parentBranchName);
        if (branchList != null) {
            branchList.get(INDEX_DATA).add(createMultiArrayBranch(arrayBranchName));
            navigationBranch.add((ArrayList) branchList.get(INDEX_DATA).get(INDEX_BRANCH_NAME));
        }
    }

    public void addBranchInGroup(String parentBranchName, String newBranchName) {
        ArrayList branchList = searchBranch(parentBranchName);
        if (groupModificationFlag && branchList != null) {
            ArrayList dataBranch = (ArrayList) branchList.get(INDEX_DATA);
            ArrayList innerGroup = searchGroup(dataBranch);
            ArrayList<Object> newBranch = createBranch(newBranchName);
            innerGroup.add(newBranch);
            navigationBranch.add(newBranch);
        }
    }

    public void addPairGroup(String parentBranchName, String pairName, Object pairValue) {
        ArrayList branchList = searchBranch(parentBranchName);
        if (branchList != null && TYPE_MULTIPLE_ARRAY.equals(branchList.get(INDEX_DATA_TYPE))) {
            ArrayList dataBranch = (ArrayList) branchList.get(INDEX_DATA);
            ArrayList innerGroup = searchGroup(dataBranch);
            if (groupModificationFlag && innerGroup != null) {
                innerGroup.add(createPair(pairName, pairValue));
            } else {
                ArrayList<Object> groupPairs = new ArrayList<Object>();
                groupPairs.add(createPair(pairName, pairValue));
                dataBranch.add(groupPairs);
                groupModificationFlag = true;
            }
        }
    }

    public void addArrayInGroup(String parentBranchName, String newArrayBranchName) {
        ArrayList<ArrayList> branchList = searchBranch(parentBranchName);
        ArrayList<Object> newBranch = createBranch(newArrayBranchName);
        newBranch.set(INDEX_DATA_TYPE, TYPE_SIMPLE_ARRAY);
        if (branchList != null && TYPE_MULTIPLE_ARRAY.equals(branchList.get(INDEX_DATA_TYPE))) {
            ArrayList dataBranch = branchList.get(INDEX_DATA);
            ArrayList innerGroup = searchGroup(dataBranch);
            if (groupModificationFlag && innerGroup != null) {
                innerGroup.add(newBranch);
                navigationBranch.add(newBranch);
            } else {
                ArrayList<Object> newGroup = new ArrayList<Object>();
                newGroup.add(newBranch);
                dataBranch.add(newGroup);
                navigationBranch.add(newBranch);
                groupModificationFlag = true;
            }
        }
    }

    public void addArrayBranch(String branchName) {
        ArrayList<Object> newBranch = createBranch(branchName);
        newBranch.set(INDEX_DATA_TYPE, TYPE_SIMPLE_ARRAY);
        schema.add(newBranch);
        navigationBranch.add(newBranch);
    }

    public void addArrayBranch(String parentBranchName, String newBranchName) {
        ArrayList<ArrayList> branchList = searchBranch(parentBranchName);
        ArrayList<Object> newBranch = createBranch(newBranchName);
        if (branchList != null) {
            newBranch.set(INDEX_DATA_TYPE, TYPE_SIMPLE_ARRAY);
            branchList.get(INDEX_DATA).add(newBranch);
            navigationBranch.add(newBranch);
        } else {
            addBranch(parentBranchName, newBranchName);
            ArrayList setBranch = searchBranch(newBranchName);
            setBranch.set(INDEX_DATA_TYPE, TYPE_SIMPLE_ARRAY);
        }
    }

    public void addArrayValue(String branchName, Object value) {
        ArrayList<ArrayList> branchList = searchBranch(branchName);
        if (branchList != null) {
            if (TYPE_SIMPLE_ARRAY.equals(branchList.get(INDEX_DATA_TYPE))) {
                branchList.get(INDEX_DATA).add(value);
            }
        } else {
            ArrayList<Object> newBranch = createBranch(branchName);
            newBranch.set(INDEX_DATA_TYPE, TYPE_SIMPLE_ARRAY);
            ArrayList data = (ArrayList) newBranch.get(INDEX_DATA);
            data.add(value);
            schema.add(newBranch);
            navigationBranch.add(newBranch);
        }
    }

    public void setAsPreviousBranch(String branchNameToSet) {
        ArrayList branchList = searchBranch(branchNameToSet);
        if (branchList != null) {
            lastBranch = branchList;
        }
    }

    public void backToPreviousBranch() {
        navigationBranch.remove(lastBranch);
        navigationBranch.add(lastBranch);
        lastBranch = null;
    }

    public void closeGroup() {
        groupModificationFlag = false;
    }

    @Override
    public String toString() {
        String str = "";
        str += "{";
        int size = schema.size();
        for (int index = 0; index < size; index++) {
            Object entry = schema.get(index);
            str += readBranch(entry);
            if (size - 1 != index) {
                str += ",";
            }
        }
        str += "}";
        return str;
    }

    private boolean checkBranchNameForNull(Object object) {
        ArrayList branch = (ArrayList) object;
        if (branch.size() >= 3 && branch.get(INDEX_BRANCH_NAME) != null) {
            return true;
        }
        return false;
    }

    private String checkBranchDataType(Object object) {
        ArrayList branch = (ArrayList) object;
        Object value = branch.get(INDEX_DATA_TYPE);
        if (value != null) {
            return (String) value;
        }
        return "";
    }

    private String readBranch(Object entry) {
        String str = "";
        if (checkBranchNameForNull(entry)) {
            String branchType = checkBranchDataType(entry);
            ArrayList branch = (ArrayList) entry;
            Object value = branch.get(INDEX_DATA);
            if (TYPE_SIMPLE_ARRAY.equals(branchType)) {
                str += "\"" + branch.get(INDEX_BRANCH_NAME) + "\":[";
                str += readSimpleArray(value) + "]";
            } else if (TYPE_MULTIPLE_ARRAY.equals(branchType)) {
                str += "\"" + branch.get(INDEX_BRANCH_NAME) + "\":[";
                str += readMultiArray(value) + "]";
            } else {
                str += "\"" + branch.get(INDEX_BRANCH_NAME) + "\":{";
                str += readValues(value) + "}";
            }
        } else {
            ArrayList parentPair = (ArrayList) entry;
            str += readValues(parentPair.get(INDEX_DATA));
        }
        return str;
    }

    private String readValues(Object value) {
        ArrayList list = (ArrayList) value;
        return readInnerValues(list);
    }

    private String readInnerValues(ArrayList list) {
        String str = "";
        for (int inner = 0; inner < list.size(); inner++) {
            Object value = list.get(inner);
            if (value instanceof LinkedHashMap) {
                str += readPair(value);
            }
            if (value instanceof ArrayList) {
                str += readBranch(value);
            }
            if (list.size() - 1 != inner) {
                str += ",";
            }
        }
        return str;
    }

    private String readSimpleArray(Object o) {
        ArrayList list = (ArrayList) o;
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += primitiveValuesToString(list.get(i));
            if (list.size() - 1 != i) {
                str += ",";
            }
        }
        return str;
    }

    private String readPair(Object object) {
        LinkedHashMap<String, Object> value = (LinkedHashMap<String, Object>) object;
        String pairToString = "";
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            pairToString = "\"" + entry.getKey() + "\":";
            Object pairValue = entry.getValue();
            pairToString += primitiveValuesToString(pairValue);
        }
        return pairToString;
    }


    private String primitiveValuesToString(Object value) {
        String str = "";
        if (value instanceof Integer || value instanceof Double || value instanceof Boolean) {
            str += value.toString();
            return str;
        } else {
            str += "\"" + value.toString() + "\"";
            return str;
        }
    }

    private String readMultiArray(Object object) {
        ArrayList<ArrayList> multiArray = (ArrayList<ArrayList>) object;
        String str = "";
        for (int outer = 0; outer < multiArray.size(); outer++) {
            str += "{";
            ArrayList innerArray = multiArray.get(outer);
            str += readInnerValues(innerArray);
            if (multiArray.size() - 1 != outer) {
                str += "},";
            } else {
                str += "}";
            }
        }
        return str;
    }

    public String toRead(String str) {
        str = str.replace("[]", "EMPTY_ARRAY");
        str = str.replace("{}", "EMPTY_BRANCH");
        str = str.replace("}}", "DOUBLE_CURLY_BRACKETS");
        str = str.replace(",", ",\n");
        str = str.replace("{", "{\n");
        str = str.replace("}", "\n}");
        str = str.replace(":", ": ");
        str = str.replace("[", "[\n");
        str = str.replace("]", "\n  ]");
        str = str.replace("EMPTY_ARRAY", "[]");
        str = str.replace("EMPTY_BRANCH", "{}");
        str = str.replace("DOUBLE_CURLY_BRACKETS", "\n  }\n}");
        return str;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(new File(filename));
        writer.write(toRead(toString()));
        writer.flush();
        writer.close();
    }

    public void serialization(Object object) throws ClassNotFoundException, IllegalAccessException {
        Class objectClass = object.getClass();
        if (checkExistBuildAnnotation(objectClass)) {
            String branchName = getBranchName(object);
            addBranch(branchName);
            getValuesFromFields(object, branchName, false);
        }
    }

    public void serialization(Object[] objects) throws IllegalAccessException, ClassNotFoundException {
        if (objects.length != 0) {
            String branchName;
            Object value = getObjectFromArray(objects);
            if (checkExistBuildAnnotation(value.getClass())) {
                branchName = getBranchName(value);
                addMultiArrayBranch(branchName);
                for (Object object : objects) {
                    getValuesFromFields(object, branchName, true);
                    closeGroup();
                }
            }
        }
    }

    private boolean checkExistBuildAnnotation(Class objectClass) throws ClassNotFoundException {
        if (objectClass.isAnnotationPresent(JsonBuild.class)) {
            return true;
        }
        throw new ClassNotFoundException("Not been declared annotation @JsonBuild in object" + objectClass.getName());
    }

    private String getBranchName(Object object) {
        Class objectClass = object.getClass();
        String branchName;
        String nameFromAnnotation = getNameFromAnnotation(objectClass.getAnnotation(JsonBuild.class));
        if (nameFromAnnotation != null) {
            branchName = nameFromAnnotation;
        } else {
            branchName = objectClass.getSimpleName();
            if (object instanceof Object[]) {
                branchName = branchName.substring(0, branchName.length() - 2);
            }
            return branchName;
        }
        return branchName;
    }

    private void getValuesFromFields(Object object, String branchName, boolean addMultiArray) throws IllegalAccessException, ClassNotFoundException {
        Class objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(JsonBuildField.class);
            if (annotation == null) {
                continue;
            }
            field.setAccessible(true);
            Object value = field.get(object);
            String nameField;
            String nameFieldFromAnnotation = getNameFromAnnotation(annotation);
            if (nameFieldFromAnnotation != null) {
                nameField = nameFieldFromAnnotation;
            } else {
                nameField = field.getName();
            }
            if (value instanceof String || value instanceof Integer ||
                    value instanceof Double || value instanceof Boolean) {
                if (addMultiArray) {
                    addPairGroup(branchName, nameField, value);
                } else {
                    addPair(branchName, nameField, value);
                }
            } else if (value instanceof String[] || value instanceof Integer[] ||
                    value instanceof Double[] || value instanceof Boolean[]) {
                if (addMultiArray) {
                    addArrayInGroup(branchName, nameField);
                } else {
                    addArrayBranch(branchName, nameField);
                }
                Object[] objects = (Object[]) value;
                for (Object objectValue : objects) {
                    addArrayValue(nameField, objectValue);
                }
            } else {
                if (addMultiArray) {
                    addBranchInGroup(branchName, nameField);
                    getValuesFromFields(value, nameField, false);
                } else {
                    addBranch(branchName, nameField);
                    getValuesFromFields(value, nameField, false);
                }
            }
        }
    }

    private String getNameFromAnnotation(Annotation annotation) {
        if (annotation != null) {
            if (annotation instanceof JsonBuild) {
                JsonBuild annotationParameters = (JsonBuild) annotation;
                String value = annotationParameters.objectName();
                if (value.length() != 0) {
                    return value;
                }
            }
            if (annotation instanceof JsonBuildField) {
                JsonBuildField annotationParameters = (JsonBuildField) annotation;
                String value = annotationParameters.fieldName();
                if (value.length() != 0) {
                    return value;
                }
            }
        }
        return null;
    }

    private Object getObjectFromArray(Object[] objects) {
        if (objects != null) {
            for (Object object : objects) {
                if (object != null) {
                    return object;
                }
            }
        }
        return null;
    }
}