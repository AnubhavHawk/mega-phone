package com.bbps.scheduler.processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bbps.scheduler.model.BillerDetails;
import com.bbps.scheduler.repository.BillerDetailsRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


//@Component
//public class MailSenderTasklet {
//
//    @Autowired
//    @Qualifier("dbJdbcTemplate")
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private BillerDetailsRepository billerDetailsRepository;
//
//    public void execute() throws Exception {
//        // TODO Auto-generated method stub
//        String query = " select * from biller_details where STR_TO_DATE(next_trigger_date, '%Y-%m-%d %H:%i')< STR_TO_DATE(due_date, '%Y-%m-%d %H:%i') and STR_TO_DATE(next_trigger_date, '%Y-%m-%d %H:%i')=STR_TO_DATE(now(), '%Y-%m-%d %H:%i');";
//        System.out.println("Mail Sender Tasklet -------------> It is running!");
//
//        List<BillerDetails> billerLists = jdbcTemplate.query(query, (rs, rowNum) -> {
//            BillerDetails billers = new BillerDetails();
//            billers.setCustParams(rs.getString("cust_params"));
//            billers.setCustomerMobile(rs.getString("biller_mobile"));
//            billers.setBillerId(rs.getString("biller_id"));
//            billers.setBillerMessage(rs.getString("biller_message"));
//            billers.setId(rs.getInt("id"));
//            billers.setNextTriggerDate(rs.getString("next_trigger_date"));
//            billers.setDueDate(rs.getString("due_date"));
//            return billers;
//        });
////        billerLists = billerDetailsRepository.findAll();
//
//        for (BillerDetails b : billerLists) {
//
//            try {
//
//                BufferedWriter fw = new BufferedWriter(
//                        new FileWriter("D:\\projects\\java-projects\\bbps-i3-ideate-hackathon\\url-shortner\\src\\main\\resources", true));
//                fw.write("To  " + b.getCustomerMobile());
//                fw.write("\n");
////                fw.write(System.getProperty("line.separator"));
//                fw.write("Body  " + b.getCustParams());
////                fw.write(System.getProperty("line.separator"));
//                fw.write("\n");
//                fw.write("-------------------------------------------------");
//                fw.write("\n");
////                fw.write(System.getProperty("line.separator"));
//                fw.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            System.out.println("Success...");
//
//            String nextDate = dateGenerator(b.getNextTriggerDate());
//            String sql = "";
//            try {
//                if (validateForUpdate(nextDate, b.getDueDate())) {
//                    sql = "delete from biller_details where id=" + b.getId();
//                    jdbcTemplate.execute(sql);
//                } else {
//                    sql = "update biller_details set next_trigger_date='" + nextDate + "' where id=" + b.getId() + ";";
//                    jdbcTemplate.update(sql);
//
//                }
//            }
//            catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    private boolean validateForUpdate(String nextTriggerDate, String dueDate) {
//        // TODO Auto-generated method stub
//        boolean flag = false;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date d1;
//            Date d2;
//
//            d1 = sdf.parse(nextTriggerDate);
//
//            d2 = sdf.parse(dueDate);
//            if (d1.compareTo(d2) == 0 || d1.compareTo(d2)>0) {
//                flag = true;
//            }
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return flag;
//    }
//
//    public String dateGenerator(String date) {
//
//        String dt = date;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar c = Calendar.getInstance();
//        try {
//            c.setTime(sdf.parse(dt));
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        c.add(Calendar.DATE, 1);
//        dt = sdf.format(c.getTime());
//        return dt;
//    }
//
//}


//@Configuration
//class DBConfig {
//
//    @Bean("dataSourceProp")
//    @ConfigurationProperties("prop.billerdb")
//    public DataSourceProperties DBDatasourceProp() {
//        return new DataSourceProperties();
//    }
//
//
//    @Bean("dbDataSource")
//    public DataSource dataSourceDb(@Autowired @Qualifier("dataSourceProp") DataSourceProperties dataProp) {
//        return dataProp.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//
//    }
//
//    @Bean("dbTransactionManager")
//    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("dbDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean("dbJdbcTemplate")
//    public JdbcTemplate jdbctemplate(@Autowired @Qualifier("dbDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//}

// ============================ Converting JDBC to JPA ======================
@Component
public class MailSenderTasklet {

//    @Autowired
//    @Qualifier("dbJdbcTemplate")
//    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BillerDetailsRepository billerDetailsRepository;

    public void execute() throws Exception {
        // TODO Auto-generated method stub
//        String query = " select * from biller_details where STR_TO_DATE(next_trigger_date, '%Y-%m-%d %H:%i')< STR_TO_DATE(due_date, '%Y-%m-%d %H:%i') and STR_TO_DATE(next_trigger_date, '%Y-%m-%d %H:%i')=STR_TO_DATE(now(), '%Y-%m-%d %H:%i');";
        System.out.println("Mail Sender Tasklet -------------> It is running!");

//        List<BillerDetails> billerLists = jdbcTemplate.query(query, (rs, rowNum) -> {
//            BillerDetails billers = new BillerDetails();
//            billers.setCustParams(rs.getString("cust_params"));
//            billers.setCustomerMobile(rs.getString("biller_mobile"));
//            billers.setBillerId(rs.getString("biller_id"));
//            billers.setBillerMessage(rs.getString("biller_message"));
//            billers.setId(rs.getInt("id"));
//            billers.setNextTriggerDate(rs.getString("next_trigger_date"));
//            billers.setDueDate(rs.getString("due_date"));
//            return billers;
//        });
        List<BillerDetails> billerLists = billerDetailsRepository.findAll();

        for (BillerDetails b : billerLists) {

            try {

                BufferedWriter fw = new BufferedWriter(
                        new FileWriter("C:\\Users\\hawka\\OneDrive\\Documents\\Sample.txt", true));
                fw.write("To  " + b.getCustomerMobile());
                fw.write("\n");
//                fw.write(System.getProperty("line.separator"));
                fw.write("Body  " + b.getCustParams());
//                fw.write(System.getProperty("line.separator"));
                fw.write("\n");
                fw.write("-------------------------------------------------");
                fw.write("\n");
//                fw.write(System.getProperty("line.separator"));
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("Success...");

            String nextDate = dateGenerator(b.getNextTriggerDate());
            String sql = "";
            try {
                if (validateForUpdate(nextDate, b.getDueDate())) {
                    billerDetailsRepository.delete(b);
//                    sql = "delete from biller_details where id=" + b.getId();
//                    jdbcTemplate.execute(sql);
                } else {
                    b.setNextTriggerDate(nextDate);
                    billerDetailsRepository.save(b);
//                    sql = "update biller_details set next_trigger_date='" + nextDate + "' where id=" + b.getId() + ";";
//                    jdbcTemplate.update(sql);

                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

    }

    private boolean validateForUpdate(String nextTriggerDate, String dueDate) {
        // TODO Auto-generated method stub
        boolean flag = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1;
            Date d2;

            d1 = sdf.parse(nextTriggerDate);

            d2 = sdf.parse(dueDate);
            if (d1.compareTo(d2) == 0 || d1.compareTo(d2)>0) {
                flag = true;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return flag;
    }

    public String dateGenerator(String date) {

        String dt = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.add(Calendar.DATE, 1);
        dt = sdf.format(c.getTime());
        return dt;
    }

}