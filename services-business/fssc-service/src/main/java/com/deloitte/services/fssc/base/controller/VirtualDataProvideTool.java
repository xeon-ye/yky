package com.deloitte.services.fssc.base.controller;

import com.deloitte.services.fssc.base.entity.AccountSimple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 虚拟数据提供工具,使用场景是开发过程中,需要使用还没有设计的功能数据
 */
@Deprecated
public class VirtualDataProvideTool {

    public static List<AccountSimple> getAccountSimple() {
        List<AccountSimple> accountSimpleList = new ArrayList<>();
        AccountSimple account1 = new AccountSimple(1L, "40010202", "科技条件专项1");
        AccountSimple account2 = new AccountSimple(2L, "40010203", "科技条件专项2");
        AccountSimple account3 = new AccountSimple(3L, "40010204", "科技条件专项3");
        AccountSimple account4 = new AccountSimple(4L, "40010205", "科技条件专项4");
        accountSimpleList.add(account1);
        accountSimpleList.add(account2);
        accountSimpleList.add(account3);
        accountSimpleList.add(account4);
        return accountSimpleList;
    }

    public static Map<Long,AccountSimple> getAccountMap(){
        List<AccountSimple> accountSimpleList = getAccountSimple();
        Map<Long,AccountSimple> accountSimpleMap = new HashMap<>(accountSimpleList.size());
        for (AccountSimple accountSimple : accountSimpleList){
            accountSimpleMap.put(accountSimple.getId(),accountSimple);
        }
        return accountSimpleMap;
    }
}
