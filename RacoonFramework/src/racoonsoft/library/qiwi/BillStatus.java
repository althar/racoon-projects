package racoonsoft.library.qiwi;

public class BillStatus
{
    // All requests result codes
    public static Integer BILL_SUCCESS = 0;
    public static Integer BILL_SERVER_IS_BUSY = 13;
    public static Integer BILL_AUTHORIZATION_ERROR = 150;
    public static Integer BILL_UNKNOWN_ERROR = 300;
    public static Integer BILL_ENCRIPTION_ERROR = 330;
    public static Integer BILL_IP_BANNED = 339;
    public static Integer BILL_TO_OFTEN = 370;

    // Create bill requests result codes
    public static Integer BILL_CREATION_ID_ALREADY_EXISTS = 215;
    public static Integer BILL_CREATION_SUM_TOO_LOW = 241;
    public static Integer BILL_CREATION_SUM_TO_HIGH = 242;
    public static Integer BILL_CREATION_AGENT_DOESNT_EXIST = 298;

    // Check bill requsets result codes
    public static Integer BILL_CHECKING_TO_OFTEN = 278;
    public static Integer BILL_CHECKING_BILL_NOT_FOUND = 210;

    // Bill statuses
    public static Integer BILL_STATUS_CREATED = 50;
    public static Integer BILL_STATUS_PROCESSING = 52;
    public static Integer BILL_STATUS_PAID = 60;
    public static Integer BILL_STATUS_CANCELED_TERMINAL = 150;
    public static Integer BILL_STATUS_CANCELED_CLIENT = 151;
    public static Integer BILL_STATUS_CANCELED_ELSE = 160;
    public static Integer BILL_STATUS_CANCELED_EXPIRED = 161;

    // ------------------------------------------------------------

    private String ID;
    private Integer Status;
    private Double Sum;

    public BillStatus(String id,Integer status,Double sum)
    {
	ID = id;
	Status = status;
	Sum = sum;
    }
    public String getID()
    {
	return ID;
    }
    public Integer getStatus()
    {
	return Status;
    }
    public Double getSum()
    {
	return Sum;
    }
}
