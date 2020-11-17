/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.brianxia.mini.seata.common.utils;

import static com.brianxia.mini.seata.common.constant.DefaultValue.SERVER_DEFAULT_PORT;


public class PortHelper {

    /**
     *
     * @param args 命令行参数
     * @return
     */
    public static int getPort(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if("-p".equalsIgnoreCase(args[i]) && args.length - 1 > i){
                return Integer.parseInt(args[i+1]);
            }
        }

        return SERVER_DEFAULT_PORT;
    }

}
