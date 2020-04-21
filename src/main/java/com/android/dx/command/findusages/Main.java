/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.dx.command.findusages;

import com.android.dex.Dex;
import com.android.dex.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public final class Main {
    public static void main(String[] args) throws IOException {
        String dexFile = args[0];
        String declaredBy = args[1];
        String memberName = args[2];
        PrintWriter out = new PrintWriter(System.out);
        if (FileUtils.hasArchiveSuffix(dexFile)) {
            List<Dex> dexList =  FileUtils.getDexListFromArchive(dexFile);
            for (Dex dex : dexList) {
                new FindUsages(dex, declaredBy, memberName, out).findUsages();
            }
        } else {
            Dex dex = new Dex(new File(dexFile));
            new FindUsages(dex, declaredBy, memberName, out).findUsages();
        }
        out.flush();
    }
}
