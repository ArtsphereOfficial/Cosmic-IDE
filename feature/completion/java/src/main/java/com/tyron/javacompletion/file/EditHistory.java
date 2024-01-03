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
package com.tyron.javacompletion.file;


import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Optional;

@AutoValue
public abstract class EditHistory {
    public static EditHistory create(String originalContent, List<AppliedEdit> appliedEdits) {
        return new AutoValue_EditHistory(originalContent, ImmutableList.copyOf(appliedEdits));
    }

    public abstract String getOriginalContent();

    public abstract ImmutableList<AppliedEdit> getAppliedEdits();

    @AutoValue
    public abstract static class AppliedEdit {
        public static AppliedEdit create(
                TextRange textRange, Optional<Integer> rangeLength, String newText) {
            return new AutoValue_EditHistory_AppliedEdit(textRange, rangeLength, newText);
        }

        public abstract TextRange getTextRange();

        public abstract Optional<Integer> getRangeLength();

        public abstract String getNewText();
    }
}
