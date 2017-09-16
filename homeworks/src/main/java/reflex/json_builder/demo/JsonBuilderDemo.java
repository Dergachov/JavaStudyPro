package reflex.json_builder.demo;

import reflex.json_builder.JsonBuilder;

import java.io.IOException;

public class JsonBuilderDemo {
    /**
     * JSON Schema for example:
     * <p>
     * {
     * "$schema": "http://json-schema.org/draft-06/schema#",
     * "$id": "http://json-schema.org/draft-06/schema#",
     * "title": "Core schema meta-schema",
     * "definitions": {
     * "schemaArray": {
     * "type": "array",
     * "minItems": 1,
     * "items": { "$ref": "#" }
     * },
     * "nonNegativeInteger": {
     * "type": "integer",
     * "minimum": 0
     * },
     * "nonNegativeIntegerDefault0": {
     * "allOf": [
     * { "$ref": "#/definitions/nonNegativeInteger" },
     * { "default": 0 }
     * ]
     * },
     * "simpleTypes": {
     * "enum": [
     * "array",
     * "boolean",
     * "integer",
     * "null",
     * "number",
     * "object",
     * "string"
     * ]
     * },
     * "stringArray": {
     * "type": "array",
     * "items": { "type": "string" },
     * "uniqueItems": true,
     * "default": []
     * }
     * },
     * "type": ["object", "boolean"],
     * "properties": {
     * "$id": {
     * "type": "string",
     * "format": "uri-reference"
     * },
     * "$schema": {
     * "type": "string",
     * "format": "uri"
     * },
     * "$ref": {
     * "type": "string",
     * "format": "uri-reference"
     * },
     * "title": {
     * "type": "string"
     * },
     * "description": {
     * "type": "string"
     * },
     * "default": {},
     * "multipleOf": {
     * "type": "number",
     * "exclusiveMinimum": 0
     * },
     * "maximum": {
     * "type": "number"
     * },
     * "exclusiveMaximum": {
     * "type": "number"
     * },
     * "minimum": {
     * "type": "number"
     * },
     * "exclusiveMinimum": {
     * "type": "number"
     * },
     * "maxLength": { "$ref": "#/definitions/nonNegativeInteger" },
     * "minLength": { "$ref": "#/definitions/nonNegativeIntegerDefault0" },
     * "pattern": {
     * "type": "string",
     * "format": "regex"
     * },
     * "additionalItems": { "$ref": "#" },
     * "items": {
     * "anyOf": [
     * { "$ref": "#" },
     * { "$ref": "#/definitions/schemaArray" }
     * ],
     * "default": {}
     * },
     * "maxItems": { "$ref": "#/definitions/nonNegativeInteger" },
     * "minItems": { "$ref": "#/definitions/nonNegativeIntegerDefault0" },
     * "uniqueItems": {
     * "type": "boolean",
     * "default": false
     * },
     * "contains": { "$ref": "#" },
     * "maxProperties": { "$ref": "#/definitions/nonNegativeInteger" },
     * "minProperties": { "$ref": "#/definitions/nonNegativeIntegerDefault0" },
     * "required": { "$ref": "#/definitions/stringArray" },
     * "additionalProperties": { "$ref": "#" },
     * "definitions": {
     * "type": "object",
     * "additionalProperties": { "$ref": "#" },
     * "default": {}
     * },
     * "properties": {
     * "type": "object",
     * "additionalProperties": { "$ref": "#" },
     * "default": {}
     * },
     * "patternProperties": {
     * "type": "object",
     * "additionalProperties": { "$ref": "#" },
     * "default": {}
     * },
     * "dependencies": {
     * "type": "object",
     * "additionalProperties": {
     * "anyOf": [
     * { "$ref": "#" },
     * { "$ref": "#/definitions/stringArray" }
     * ]
     * }
     * },
     * "propertyNames": { "$ref": "#" },
     * "const": {},
     * "enum": {
     * "type": "array",
     * "minItems": 1,
     * "uniqueItems": true
     * },
     * "type": {
     * "anyOf": [
     * { "$ref": "#/definitions/simpleTypes" },
     * {
     * "type": "array",
     * "items": { "$ref": "#/definitions/simpleTypes" },
     * "minItems": 1,
     * "uniqueItems": true
     * }
     * ]
     * },
     * "format": { "type": "string" },
     * "allOf": { "$ref": "#/definitions/schemaArray" },
     * "anyOf": { "$ref": "#/definitions/schemaArray" },
     * "oneOf": { "$ref": "#/definitions/schemaArray" },
     * "not": { "$ref": "#" }
     * },
     * "default": {}
     * }
     */
    public static void main(String[] args) {

        JsonBuilder doc = new JsonBuilder();

        /**
         *{
         "$schema": "http://json-schema.org/draft-06/schema#",
         "$id": "http://json-schema.org/draft-06/schema#",
         "title": "Core schema meta-schema",
         */
        doc.addParentPair("$schema", "http://json-schema.org/draft-06/schema#");
        doc.addParentPair("$id", "http://json-schema.org/draft-06/schema#");
        doc.addParentPair("title", "Core schema meta-schema");

        /**
         "definitions": {
         "schemaArray": {
         "type": "array",
         "minItems": 1,
         "items": { "$ref": "#" }
         },
         */
        doc.addBranch("definitions");
        doc.addBranch("definitions", "schemaArray");
        doc.addPair("schemaArray", "type", "array");
        doc.addPair("schemaArray", "minItems", 1);
        doc.addBranch("schemaArray", "items");
        doc.addPair("items", "$ref", "#");

        /**
         "nonNegativeInteger": {
         "type": "integer",
         "minimum": 0
         },
         */
        doc.addBranch("definitions", "nonNegativeInteger");
        doc.addPair("nonNegativeInteger", "type", "integer");
        doc.addPair("nonNegativeInteger", "minimum", 0);

        /**
         "nonNegativeIntegerDefault0": {
         "allOf": [
         { "$ref": "#/definitions/nonNegativeInteger" },
         { "default": 0 }
         ]
         },*/
        doc.addBranch("definitions", "nonNegativeIntegerDefault0");
        doc.addMultiArrayBranch("nonNegativeIntegerDefault0", "allOf");
        doc.addPairGroup("allOf", "$ref", "#/definitions/nonNegativeInteger");
        doc.closeGroup();
        doc.addPairGroup("allOf", "default", 0);
        doc.closeGroup();

        /**
         "simpleTypes": {
         "enum": [
         "array",
         "boolean",
         "integer",
         "null",
         "number",
         "object",
         "string"
         ]
         },*/
        doc.addBranch("definitions", "simpleTypes");
        doc.addArrayBranch("simpleTypes", "enum");
        doc.addArrayValue("enum", "array");
        doc.addArrayValue("enum", "boolean");
        doc.addArrayValue("enum", "integer");
        doc.addArrayValue("enum", "null");
        doc.addArrayValue("enum", "number");
        doc.addArrayValue("enum", "object");
        doc.addArrayValue("enum", "string");

        /**
         "stringArray": {
         "type": "array",
         "items": { "type": "string" },
         "uniqueItems": true,
         "default": []
         }
         },*/
        doc.addBranch("definitions", "stringArray");
        doc.addPair("stringArray", "type", "array");
        doc.addBranch("stringArray", "items");
        doc.addPair("items", "type", "string");
        doc.addPair("stringArray", "uniqueItems", true);
        doc.addArrayBranch("stringArray", "default");

        /**"type": ["object", "boolean"], */
        doc.addArrayBranch("type");
        doc.addArrayValue("type", "object");
        doc.addArrayValue("type", "boolean");


        /**
         "properties": {
         */
        doc.addBranch("properties");

        /**
         "$id": {
         "type": "string",
         "format": "uri-reference"
         },
         "$schema": {
         "type": "string",
         "format": "uri"
         },
         "$ref": {
         "type": "string",
         "format": "uri-reference"
         },
         */
        doc.addBranch("properties", "$id");
        doc.addPair("$id", "type", "string");
        doc.addPair("$id", "format", "uri-reference");

        doc.addBranch("properties", "$schema");
        doc.addPair("$schema", "type", "string");
        doc.addPair("$schema", "format", "uri");

        doc.addBranch("properties", "$ref");
        doc.addPair("$ref", "type", "string");
        doc.addPair("$ref", "format", "uri-reference");

        /**
         "title": {
         "type": "string"
         },
         "description": {
         "type": "string"
         },*/
        doc.addBranch("properties", "title");
        doc.addPair("title", "type", "string");

        doc.addBranch("properties", "description");
        doc.addPair("description", "type", "string");

        /**
         "default": {},
         "multipleOf": {
         "type": "number",
         "exclusiveMinimum": 0
         },
         "maximum": {
         "type": "number"
         },
         "exclusiveMaximum": {
         "type": "number"
         },
         "minimum": {
         "type": "number"
         },
         "exclusiveMinimum": {
         "type": "number"
         },*/
        doc.addBranch("properties", "default");
        doc.addBranch("properties", "multipleOf");
        doc.addPair("multipleOf", "type", "number");
        doc.addPair("multipleOf", "exclusiveMinimum", 0);

        doc.addBranch("properties", "maximum");
        doc.addPair("maximum", "type", "number");

        doc.addBranch("properties", "exclusiveMaximum");
        doc.addPair("exclusiveMaximum", "type", "number");

        doc.addBranch("properties", "minimum");
        doc.addPair("minimum", "type", "number");

        doc.addBranch("properties", "exclusiveMinimum");
        doc.addPair("exclusiveMinimum", "type", "number");

        /**
         "maxLength": { "$ref": "#/definitions/nonNegativeInteger" },
         "minLength": { "$ref": "#/definitions/nonNegativeIntegerDefault0" },
         "pattern": {
         "type": "string",
         "format": "regex"
         },
         "additionalItems": { "$ref": "#" },
         */
        doc.addBranch("properties", "maxLength");
        doc.addPair("maxLength", "$ref", "#/definitions/nonNegativeInteger");
        doc.addBranch("properties", "minLength");
        doc.addPair("minLength", "$ref", "#/definitions/nonNegativeIntegerDefault0");

        doc.addBranch("properties", "pattern");
        doc.addPair("pattern", "type", "string");
        doc.addPair("pattern", "format", "regex");

        doc.addBranch("properties", "additionalItems");
        doc.addPair("additionalItems", "$ref", "#");
        /**
         "items": {
         "anyOf": [
         { "$ref": "#" },
         { "$ref": "#/definitions/schemaArray" }
         ],
         "default": {}
         },*/
        doc.addBranch("properties", "items");
        doc.addMultiArrayBranch("items", "anyOf");
        doc.addPairGroup("anyOf", "$ref", "#");
        doc.closeGroup();
        doc.addPairGroup("anyOf", "$ref", "#/definitions/schemaArray");
        doc.closeGroup();
        doc.addBranch("items", "default");

        /**
         "maxItems": { "$ref": "#/definitions/nonNegativeInteger" },
         "minItems": { "$ref": "#/definitions/nonNegativeIntegerDefault0" },
         "uniqueItems": {
         "type": "boolean",
         "default": false
         },*/
        doc.addBranch("properties", "maxItems");
        doc.addPair("maxItems", "$ref", "#/definitions/nonNegativeInteger");
        doc.addBranch("properties", "minItems");
        doc.addPair("minItems", "$ref", "#/definitions/nonNegativeIntegerDefault0");
        doc.addBranch("properties", "uniqueItems");
        doc.addPair("uniqueItems", "type", "boolean");
        doc.addPair("uniqueItems", "default", false);

        /**
         "contains": { "$ref": "#" },
         "maxProperties": { "$ref": "#/definitions/nonNegativeInteger" },
         "minProperties": { "$ref": "#/definitions/nonNegativeIntegerDefault0" },
         "required": { "$ref": "#/definitions/stringArray" },
         "additionalProperties": { "$ref": "#" },
         "definitions": {
         "type": "object",
         "additionalProperties": { "$ref": "#" },
         "default": {}
         },*/
        doc.addBranch("properties", "contains");
        doc.addPair("contains", "$ref", "#");
        doc.addBranch("properties", "maxProperties");
        doc.addPair("maxProperties", "$ref", "#/definitions/nonNegativeInteger");
        doc.addBranch("properties", "minProperties");
        doc.addPair("minProperties", "$ref", "#/definitions/nonNegativeIntegerDefault0");
        doc.addBranch("properties", "required");
        doc.addPair("required", "$ref", "#/definitions/stringArray");
        doc.addBranch("properties", "additionalProperties");
        doc.addPair("additionalProperties", "$ref", "#");

        doc.addBranch("properties", "definitions");
        doc.addPair("definitions", "type", "object");
        doc.addBranch("definitions", "additionalProperties");
        doc.addPair("additionalProperties", "$ref", "#");
        doc.addBranch("definitions", "default");

        /**
         "properties": {
         "type": "object",
         "additionalProperties": { "$ref": "#" },
         "default": {}
         },
         "patternProperties": {
         "type": "object",
         "additionalProperties": { "$ref": "#" },
         "default": {}
         },*/
        doc.setAsPreviousBranch("properties");
        doc.addBranch("properties", "properties");
        doc.addPair("properties", "type", "object");
        doc.addBranch("properties", "additionalProperties");
        doc.addPair("additionalProperties", "$ref", "#");
        doc.addBranch("properties", "default");
        doc.backToPreviousBranch();


        doc.addBranch("properties", "patternProperties");
        doc.addPair("patternProperties", "type", "object");
        doc.addBranch("patternProperties", "additionalProperties");
        doc.addPair("additionalProperties", "$ref", "#");
        doc.addBranch("patternProperties", "default");

        /**
         "dependencies": {
         "type": "object",
         "additionalProperties": {
         "anyOf": [
         { "$ref": "#" },
         { "$ref": "#/definitions/stringArray" }
         ]
         }
         },*/
        doc.addBranch("properties", "dependencies");
        doc.addPair("dependencies", "type", "object");
        doc.addBranch("dependencies", "additionalProperties");
        doc.addMultiArrayBranch("additionalProperties", "anyOf");
        doc.addPairGroup("anyOf", "$ref", "#");
        doc.closeGroup();
        doc.addPairGroup("anyOf", "$ref", "#/definitions/stringArray");
        doc.closeGroup();

        /**
         "propertyNames": { "$ref": "#" },
         "const": {},
         "enum": {
         "type": "array",
         "minItems": 1,
         "uniqueItems": true
         },*/
        doc.addBranch("properties", "propertyNames");
        doc.addPair("propertyNames", "$ref", "#");
        doc.addBranch("properties", "const");
        doc.addBranch("properties", "enum");
        doc.addPair("enum", "type", "array");
        doc.addPair("enum", "minItems", 1);
        doc.addPair("enum", "uniqueItems", true);

        /**
         "type": {
         "anyOf": [
         { "$ref": "#/definitions/simpleTypes" },
         {
         "type": "array",
         "items": { "$ref": "#/definitions/simpleTypes" },
         "minItems": 1,
         "uniqueItems": true
         }
         ]
         },*/
        doc.addBranch("properties", "type");
        doc.addMultiArrayBranch("type", "anyOf");
        doc.addPairGroup("anyOf", "$ref", "#/definitions/simpleTypes");
        doc.closeGroup();
        doc.addPairGroup("anyOf", "type", "array");
        doc.addBranchInGroup("anyOf", "items");
        doc.addPair("items", "$ref", "#/definitions/simpleTypes");
        doc.addPairGroup("anyOf", "minItems", 1);
        doc.addPairGroup("anyOf", "uniqueItems", true);
        doc.closeGroup();

        /**
         "format": { "type": "string" },
         "allOf": { "$ref": "#/definitions/schemaArray" },
         "anyOf": { "$ref": "#/definitions/schemaArray" },
         "oneOf": { "$ref": "#/definitions/schemaArray" },
         "not": { "$ref": "#" }
         },
         "default": {}
         }
         */
        doc.addBranch("properties", "format");
        doc.addPair("format", "type", "string");
        doc.addBranch("properties", "allOf");
        doc.addPair("allOf", "$ref", "#/definitions/schemaArray");
        doc.addBranch("properties", "anyOf");
        doc.addPair("anyOf", "$ref", "#/definitions/schemaArray");
        doc.addBranch("properties", "oneOf");
        doc.addPair("oneOf", "$ref", "#/definitions/schemaArray");
        doc.addBranch("properties", "not");
        doc.addPair("not", "$ref", "#");

        doc.addBranch("default");

        System.out.println(doc.toString() + "\n");
        System.out.println(doc.toRead(doc.toString()));

        try {
            String file = "json_schema.json";
            doc.saveToFile(file);
            System.out.println("\nSave to file: " + file + "\nComplete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}