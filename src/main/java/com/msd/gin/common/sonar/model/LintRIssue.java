package com.msd.gin.common.sonar.model;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LintRIssue {

    String filename;
    @SerializedName("line_number")
    int lineNumber;
    @SerializedName("column_number")
    int columnNumber;
    String line;
    String type;
    String message;
    String linter;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLinter() {
        return linter;
    }

    public void setLinter(String linter) {
        this.linter = linter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("filename", filename)
                .append("lineNumber", lineNumber)
                .append("columnNumber", columnNumber)
                .append("line", line)
                .append("type", type)
                .append("message", message)
                .append("linter", linter)
                .toString();
    }
}
