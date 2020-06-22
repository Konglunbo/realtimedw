package com.zxxj.mock;
import com.zxxj.commons.model.ProductInfo;
import com.zxxj.commons.model.UserAction;
import com.zxxj.commons.model.UserInfo;
import com.zxxj.utils.HikaricpUtils;
import com.github.javafaker.Faker;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author shkstart
 * @create 2020-06-17 13:52
 */
public class MockDB {
    private static void mockUserAction() throws InterruptedException, SQLException {
        Faker fakerZH = new Faker(new Locale("zh-CN"));

        List<String> searchKeywords = Arrays.asList("马克杯","华为手机", "Mac笔记本", "小龙虾", "卫生纸", "吸尘器", "小爱音箱", "Flink", "苹果", "洗面奶");

        // 目前关注四个行为：搜索、点击、下单、支付
        List<String> actions = Arrays.asList("search", "click", "order", "pay");

        // yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        QueryRunner qr =  new QueryRunner(HikaricpUtils.getDataSource());
        String sql = "";
        while (true){

            int userNum = fakerZH.number().numberBetween(0, 10);

            //userNum个用户
            for(int i = 1; i <= userNum; i++){

                Long user_id = Long.parseLong(fakerZH.number().numberBetween(1, 101)+"");

                int sessionNum = fakerZH.number().numberBetween(0, 8);

                //每个用户sessionNum个session
                for(int j = 1; j <= sessionNum; j++){

                    String session_id = UUID.randomUUID().toString().replace("-", "");
                    // 每个(userid + sessionid)生成0-30条用户访问数据

                    int actionNum = fakerZH.number().numberBetween(0, 31);

                    for (int k = 1; k <= actionNum; k++) {

                        String date = sdf.format(new Date());
                        UserAction userAction = UserAction
                                .builder()
                                .date(date)
                                .user_id(user_id)
                                .session_id(session_id)
                                .page_id(Long.parseLong(fakerZH.number().numberBetween(0, 200)+""))
                                .action_time(System.currentTimeMillis()+"")
                                .city_id(Long.parseLong(fakerZH.number().numberBetween(0, 10)+""))
                                .build();

                        // 随机确定用户在当前session中的行为
                        String action = actions.get(fakerZH.number().numberBetween(0, 4));
                        // 根据随机产生的用户行为action决定对应字段的值
                        switch(action){
                            case "search" :
                                userAction.setSearch_keyword(searchKeywords.get(fakerZH.number().numberBetween(0, 9)));
                                sql = "insert into test.user_action(date,user_id,session_id,page_id,action_time,search_keyword,city_id) values(?,?,?,?,?,?,?)";
                                qr.execute(sql,userAction.getDate(),userAction.getUser_id(),userAction.getSession_id(),userAction.getPage_id(),userAction.getAction_time(),userAction.getSearch_keyword(),userAction.getCity_id());
                                break;
                            case "click" :
                                userAction.setClick_category_id(Long.parseLong(fakerZH.number().numberBetween(1, 101)+""));
                                userAction.setClick_product_id(Long.parseLong(fakerZH.number().numberBetween(1, 101)+""));
                                sql = "insert into test.user_action(date,user_id,session_id,page_id,action_time,click_category_id,click_product_id,city_id) values(?,?,?,?,?,?,?,?)";
                                qr.execute(sql,userAction.getDate(),userAction.getUser_id(),userAction.getSession_id(),userAction.getPage_id(),userAction.getAction_time(),userAction.getClick_category_id(),userAction.getClick_product_id(),userAction.getCity_id());

                                break;
                            case "order" :
                                userAction.setOrder_category_ids(fakerZH.number().numberBetween(1, 101)+"");
                                userAction.setOrder_product_ids(fakerZH.number().numberBetween(1, 101)+"");
                                sql = "insert into test.user_action(date,user_id,session_id,page_id,action_time,order_category_ids,order_product_ids,city_id) values(?,?,?,?,?,?,?,?)";
                                qr.execute(sql,userAction.getDate(),userAction.getUser_id(),userAction.getSession_id(),userAction.getPage_id(),userAction.getAction_time(),userAction.getOrder_category_ids(),userAction.getOrder_product_ids(),userAction.getCity_id());

                                break;
                            case "pay" :
                                userAction.setPay_category_ids(fakerZH.number().numberBetween(1, 101)+"");
                                userAction.setPay_product_ids(fakerZH.number().numberBetween(1, 101)+"");
                                sql = "insert into test.user_action(date,user_id,session_id,page_id,action_time,pay_category_ids,pay_product_ids,city_id) values(?,?,?,?,?,?,?,?)";
                                qr.execute(sql,userAction.getDate(),userAction.getUser_id(),userAction.getSession_id(),userAction.getPage_id(),userAction.getAction_time(),userAction.getPay_category_ids(),userAction.getPay_product_ids(),userAction.getCity_id());
                                break;
                            default :
                                System.out.println("未知action"+action);
                        }

                        //System.out.println(userAction.toString());


                    }

                }

            }

            Thread.sleep(fakerZH.number().numberBetween(0, 51)*100);
        }
    }


    /**
     * 模拟用UserInfo数据(一次性模拟100个)
     */
    private static void mockUserInfo() throws SQLException {

        Faker fakerZH = new Faker(new Locale("zh-CN"));
        Faker fakerEN = new Faker(new Locale("en"));
        List<String> professionalNames = Arrays.asList("学生", "老师", "程序员", "文员", "老板", "公务员", "个体户", "自由职业者", "全职妈妈", "收银员");
        List<String> sexes = Arrays.asList("male", "female");

        QueryRunner qr =  new QueryRunner(HikaricpUtils.getDataSource());
        String sql = "insert into test.user_info(user_id,user_name,name,age,professional,city,sex) values(?,?,?,?,?,?,?)";
        Object params[][] = new Object[100][];

        //循环插入数据
        for (int i = 1; i <= 100; i++) {
            //生成模拟数据
            UserInfo userInfo = UserInfo.builder()
                    .userid(Long.parseLong(i+""))
                    .username(fakerEN.name().username())
                    .name(fakerZH.name().name())
                    .age(fakerZH.number().numberBetween(18, 61))
                    .professional(professionalNames.get(fakerZH.number().numberBetween(0, 10)))
                    .city(fakerZH.address().city())
                    .sex(sexes.get(fakerZH.number().numberBetween(0, 2)))
                    .build();

            params[i-1] = new Object[] {
                    userInfo.getUserid(),
                    userInfo.getUsername(),
                    userInfo.getName(),
                    userInfo.getAge(),
                    userInfo.getProfessional(),
                    userInfo.getCity(),
                    userInfo.getSex()
            };

            //System.out.println(userInfo.toBuilder());

        }

        qr.batch(sql, params);
    }

    /**
     * 模拟ProductInfo数据
     */
    private static void mockProductInfo() throws SQLException {
        Faker fakerZH = new Faker(new Locale("zh-CN"));
        List<Integer> productStatus = Arrays.asList(0, 1);

        QueryRunner qr =  new QueryRunner(HikaricpUtils.getDataSource());

        String sql = "insert into test.product_info(product_id,product_name,extend_info) values(?,?,?)";
        Object params[][] = new Object[100][];
        //循环插入数据
        for (int i = 1; i <= 100; i++) {
            ProductInfo productInfo = ProductInfo
                    .builder()
                    .product_id(Long.parseLong(i+""))
                    .product_name("product"+i)
                    .extend_info(productStatus.get(fakerZH.number().numberBetween(0, 2)) + "")
                    .build();
            //System.out.println(productInfo);
            params[i-1] = new Object[] {
                    productInfo.getProduct_id(),
                    productInfo.getProduct_name(),
                    productInfo.getExtend_info()
            };
        }

        qr.batch(sql, params);

    }

    public static void main(String[] args) throws Exception {
//        mockUserInfo();
        mockProductInfo();
//        mockUserAction();
    }

}
