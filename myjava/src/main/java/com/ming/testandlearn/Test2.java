package com.ming.testandlearn;

/**
 * Created by hongyongming on 2016/3/2.
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
//        string_1 str = null;
//        System.out.println(string_1.format("hello:%s",str));
        String sqlHeader = "INSERT IGNORE INTO `user` " +
                "(`id`, `userZnName`, `headIcon`, `education`, `gender`, " +
                "`departmentId`, `status`, `password`, `fax`, `email`, " +
                "`phone`, `addr`, `mobilePhone`, `pwRandom`, `roleIds`," +
                " `jobDate`, `leaveJobDate`, `birthday`, `updateUser`, `updateTime`," +
                " `createUser`, `createTime`) " +
                "VALUES " ;

        genValuesClause(sqlHeader,'V');
    }

    private static void genValuesClause(String sqlHeader ,char LastColName) {
        int last = LastColName;
        String cellFomart = "IF(%s=\"\",\"null\",CONCATENATE(\"'\",%s,\"'\"))";
        StringBuilder sb = new StringBuilder();
        for (int index ='A'; index <= last; index++) {
            char cur = (char)(index);
            String value = String.format(cellFomart,cur+"1",cur+"1");
            //System.out.println(value);
            if(index =='A'){
                sb.append(String.format("=CONCATENATE(\"%s\" (%s,\",\",",sqlHeader,value));
            }else if(index == last){
                sb.append(String.format("%s))",value));
            }
            else {
                sb.append(String.format("%s,\",\",",value));
            }
        }
        System.out.println(sb.toString());
    }

}
