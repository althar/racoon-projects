package racoonsoft.languagebox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.access.ActionResultCode;
import racoonsoft.library.database.DBRecord;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MarketService extends LanguageBoxService
{
    public ArrayList<DBRecord> getSells(Long user_id) throws Exception
    {
        return dbProc.getRecords("SELECT * FROM transaction WHERE type='TEACHER_FEE' AND status='APPROVED' AND user_id="+user_id);
    }
    public ActionResult purchaseCourse(Long user_id,Long course_id) throws Exception
    {
        ActionResult result = new ActionResult(ActionResultCode.ACTION_SUCCESSFUL);
        DBRecord course = dbProc.getRecord("course","course_id="+course_id,null);
        if(course!=null)// Exists...
        {
            return new ActionResult(ActionResultCode.ACTION_FAILED_DOES_NOT_EXIST);
        }
        Double price = course.getDoubleValue("price");
        DBRecord purchase = dbProc.getRecord("course_purchase","user_id="+user_id+" AND course_id="+course_id,null);
        if(purchase!=null)// Exists...
        {
            return new ActionResult(ActionResultCode.ACTION_FAILED_ALREADY_EXISTS);
        }
        Double sellerShare = 0.3;
        Double sellerFee = price*sellerShare;
        Double serviceFee = price-sellerFee;

        Savepoint savepoint = dbProc.setSavepoint("purchase");

        try
        {
            // 1 - Create transaction add_balance
            HashMap<String,Object> addBalanceTransaction = new HashMap<String, Object>();
            addBalanceTransaction.put("user_id",user_id);
            addBalanceTransaction.put("type","ADD_BALANCE");
            addBalanceTransaction.put("value",price);
            addBalanceTransaction.put("description","Внесение денег на счет для покупки курса. id="+course.getID()+" name="+course.getStringValue("name"));
            Long addBalanceTransactionId = dbProc.executeInsert("course_purchase",addBalanceTransaction);
            result.setData("add_balance_transaction_id",addBalanceTransactionId);

            // 2 - Create transaction course_purchase
            HashMap<String,Object> paymentTransaction = new HashMap<String, Object>();
            paymentTransaction.put("user_id",user_id);
            paymentTransaction.put("type","COURSE_PURCHASE");
            paymentTransaction.put("value",-price);
            paymentTransaction.put("description","Покупка курса. id="+course.getID()+" name="+course.getStringValue("name"));
            Long coursePurchaseTransactionId = dbProc.executeInsert("course_purchase",paymentTransaction);
            result.setData("course_purchase_transaction_id",coursePurchaseTransactionId);

            // 3 - Create transaction seller_fee
            HashMap<String,Object> sellerFeeTransaction = new HashMap<String, Object>();
            sellerFeeTransaction.put("user_id",user_id);
            sellerFeeTransaction.put("type","SELLER_FEE");
            sellerFeeTransaction.put("value",sellerFee);
            sellerFeeTransaction.put("description","Прибыль правообладателя от продажи курса. id="+course.getID()+" name="+course.getStringValue("name"));
            Long sellerFeeTransactionId = dbProc.executeInsert("course_purchase",sellerFeeTransaction);
            result.setData("seller_fee_transaction_id",sellerFeeTransactionId);

            // 4 - Create transaction service_fee
            HashMap<String,Object> serviceFeeTransaction = new HashMap<String, Object>();
            serviceFeeTransaction.put("user_id",user_id);
            serviceFeeTransaction.put("type","SERVICE_FEE");
            serviceFeeTransaction.put("value",serviceFee);
            serviceFeeTransaction.put("description","Прибыль сервиса от продажи курса. id="+course.getID()+" name="+course.getStringValue("name"));
            Long serviceFeeTransactionId = dbProc.executeInsert("course_purchase",serviceFeeTransaction);
            result.setData("service_fee_transaction_id",serviceFeeTransactionId);

            // 5 - Create course_purchase
            HashMap<String,Object> coursePurchase = new HashMap<String, Object>();
            coursePurchase.put("user_id",user_id);
            coursePurchase.put("course_id",course_id);
            coursePurchase.put("course_purchase_transaction_id",coursePurchaseTransactionId);
            coursePurchase.put("seller_fee_transaction_id",sellerFeeTransactionId);
            coursePurchase.put("service_fee_transaction_id",serviceFeeTransactionId);
            Long coursePurchaseId = dbProc.executeInsert("course_purchase",coursePurchase);
            result.setData("course_purchase_id",coursePurchaseId);

            return result;
        }
        catch(Exception ex)
        {
            dbProc.rollback(savepoint);
            result.setResult(ActionResultCode.ACTION_FAILED);
            result.setData("exception",ex);
            return result;
        }
    }
}
