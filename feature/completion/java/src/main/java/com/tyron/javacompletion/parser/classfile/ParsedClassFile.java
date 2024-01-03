/*
 * This file is part of Cosmic IDE.
 * Cosmic IDE is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * Cosmic IDE is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Cosmic IDE. If not, see <https://www.gnu.org/licenses/>.
 */

/*
 *  This file is part of CodeAssist.
 *
 *  CodeAssist is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CodeAssist is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with CodeAssist.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.tyron.javacompletion.parser.classfile;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.tyron.javacompletion.model.Entity;
import com.tyron.javacompletion.model.TypeReference;

import java.util.Optional;

/**
 * Information about the parsed .class file
 */
@AutoValue
public abstract class ParsedClassFile {
    public static Builder builder() {
        return new AutoValue_ParsedClassFile.Builder();
    }

    /**
     * foo/bar/EnclosingClass$SimpleName
     *
     * <p>See JVMS 4.2.1: <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.2.1">...</a>
     */
    public abstract String getClassBinaryName();

    public abstract String getSimpleName();

    public abstract ImmutableList<String> getClassQualifiers();

    public abstract Optional<String> getOuterClassBinaryName();

    public abstract ClassSignature getClassSignature();

    public abstract ImmutableList<ParsedMethod> getMethods();

    public abstract ImmutableList<ParsedField> getFields();

    public abstract Entity.Kind getEntityKind();

    public abstract boolean isStatic();

    @AutoValue
    public abstract static class ParsedMethod {
        public static ParsedMethod create(
                String simpleName, MethodSignature signature, boolean isStatic) {
            return new AutoValue_ParsedClassFile_ParsedMethod(simpleName, signature, isStatic);
        }

        public abstract String getSimpleName();

        public abstract MethodSignature getMethodSignature();

        public abstract boolean isStatic();
    }

    @AutoValue
    public abstract static class ParsedField {
        public static ParsedField create(String simpleName, TypeReference fieldType, boolean isStatic) {
            return new AutoValue_ParsedClassFile_ParsedField(simpleName, fieldType, isStatic);
        }

        public abstract String getSimpleName();

        public abstract TypeReference getFieldType();

        public abstract boolean isStatic();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setClassBinaryName(String value);

        public abstract Builder setSimpleName(String value);

        public abstract Builder setClassQualifiers(ImmutableList<String> value);

        public abstract Builder setOuterClassBinaryName(Optional<String> value);

        public abstract Builder setClassSignature(ClassSignature value);

        public abstract ImmutableList.Builder<ParsedMethod> methodsBuilder();

        public void addMethod(ParsedMethod method) {
            methodsBuilder().add(method);
        }

        public abstract ImmutableList.Builder<ParsedField> fieldsBuilder();

        public void addField(ParsedField field) {
            fieldsBuilder().add(field);
        }

        public abstract Builder setEntityKind(Entity.Kind value);

        public abstract Builder setStatic(boolean value);

        public abstract ParsedClassFile build();
    }
}
