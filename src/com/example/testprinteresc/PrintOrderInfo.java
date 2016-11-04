package com.example.testprinteresc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
/**
 * 打印订单信息
 * @author Administrator
 *
 */
public class PrintOrderInfo {
	
	//打印产品中信息的类型
	public static String PRINT_PRODUCT_TYPE_MODIFIER="modifier";//制作要求
	//送餐类型名称
	public static String HOME_DELIVERY="外送";
	public static String ORDER_TAKEOUT="外带";
	//标题类型
	public static String RESTAURANT_COUPLET="餐厅联";
	public static String DELIVERYMAN_COUPLET="骑手联"; 
	public static String CUSTOMER_COUPLET="客户联";
	
	//取消订单
	public static String ORDER_CANCEL="取消单";
	//申请退款
	public static String ORDER_REFUND="申请退款";
	//订单废除状态
	public static String ORDER_ABOLISHED_STATUS_ID="112";
	
	//小票抬头标识（废除和取消）
	public static String HEADER_MSG_ABLISHED="废除";
	public static String HEADER_MSG_ABLISHED_CODE="ABLISHED_CODE";
	public static String HEADER_MSG_UPDATE="修改";
	
	private String headerDes;//打印的头描述
	private String orderId;
	private String orderNumber;//订单流水号
	private String orderCode;
	private String shopName;
	private String channelName;
	private String transportTime;
	private String estimatedDeliveryTime;
	private String orderTime;
	private String lastUpDateTime;
	private String enumStyleName;
	private String contactPerson;
	private String contactPhone;
	private String confirmPhone;
	private String payMethod;
	private String invoiceType;// 发票类型
	private String invoiceHeader;// 发票抬头
	private String userComments;
	private String comment; //用户备注
	private String statusComment; //取消原因
	private String deliveryAddress;// 送餐地址
	private String deliveryAddressComments;// 送餐地址备注
	private String needTableware;// 是否需要餐具
	private String totalProductQuantity;
	private String totalAmount; //订单金额
	private String businessAmount; //商家应收
	private String commissionAmount; //佣金金额
	private String commissionProportion; //佣金比例
	private String businessFavourableAmount; //商户平摊优惠金额
	private String platformFavourableAmount; //平台平摊优惠金额
	private String totalPayAmount; //付款金额
	private String deliveryAmount; //配送费
	private String boxAmount; //餐盒费
	private String deliveryStatus; //配送状态
	private String deliveryPerson; //骑手称呼
	private String deliveryPhone; //骑手电话
	private String totalSubAmount;//优惠金额
	private String favDetail;  	  //优惠明细
	private String deliverymanSign;// 骑手签名
	private String splitOrderComments;// 打印大订单 拆单备注
	private String needAccount;// 是否是结账订单
	private String hotlinephone;// 外卖电话
	private String orderSeq;// 订单流水号
	private String estimatedDeliveryFlag;//预处理标志
    private String restUnitePrintContent;//餐厅联打印内容
    private String multiUnitePrintContent;//多联打印内容
    private String statusId;
    private String headerMsg;
	
	private Map  productMap=new HashMap();
    private List productList=new ArrayList();//要打印的产品列表 
    private String processingTime1;

    public String getProcessingTime1() {
        return processingTime1;
    }

    public void setProcessingTime1(String processingTime1) {
        this.processingTime1 = processingTime1;
    }

    /**
	 * 解析json字符串为对象
	 * @param json
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void  parseJson(String json){
		//解析订单头
		JSONObject jso = (JSONObject) JSONValue.parse(json);
		
//		setHeaderDes(dealString(jso.get("headerDes")));
		setOrderId(dealString(jso.get("orderId")));//订单编号
		setOrderNumber(dealString(jso.get("orderNumber")));//订单流水号
		setOrderCode(dealString(jso.get("orderId")));
		setTransportTime(dealString(jso.get("sendToTime")));//送达时间
//		setEstimatedDeliverytTime(dealString(jso.get("estimatedDeliveryTime")));
		setShopName(dealString(jso.get("shopName"))); //门店名称
		setChannelName(dealString(jso.get("channelName"))); //渠道名称
		setOrderTime(dealString(jso.get("orderTimeStr")));	//订单时间
		setLastUpDateTime(dealString(jso.get("lastUpdateDttmString"))); //更新时间
		
		Object dineType = jso.get("dineType");
		JSONObject jsDineType = (JSONObject) JSONValue.parse(dineType.toString());
		setEnumStyleName(dealString(jsDineType.get("text")));//就餐类型
		
		
		setContactPerson(dealString(jso.get("userName")));//联系人
		setContactPhone(dealString(jso.get("orderPhone")));//联系电话
		setConfirmPhone(dealString(jso.get("orderPhone")));//确认联系电话
		
		
		Object paymentMethodNew = jso.get("paymentMethodNew");
		JSONObject jsPaymentMethodNew = (JSONObject) JSONValue.parse(paymentMethodNew.toString());
		
		setPayMethod(dealString(jsPaymentMethodNew.get("text")));//支付方式
		setInvoiceType(dealString(jso.get("invoiceType")));//发票类型
		setComment(dealString(jso.get("comment")));
		setStatusComment(dealString(jso.get("statusComment")));
		setUserComments(dealString(jso.get("userComments")));//用户备注
		setInvoiceHeader(dealString(jso.get("invoiceHeader")));//发票抬头
		setDeliveryAddress(dealString(jso.get("address")));// 送餐地址
		setDeliveryAddressComments(dealString(jso.get("HHEHHHHHHHH")));
		setNeedTableware(dealString("Y"));// 是否需要餐具
		
		setTotalProductQuantity(dealString(""));//菜品总数
		setTotalAmount(dealString(jso.get("totalAmount")));//订单金额
		setBusinessAmount(dealString(jso.get("businessAmount")));
		setDeliveryAmount(dealString(jso.get("deliveryAmount")));//配送费
		setBoxAmount(dealString(jso.get("boxAmount")));//餐盒费
		setDeliveryStatus(dealString(jso.get("deliveryStatusComment")));//配送状态
		JSONArray orderDeliveries = (JSONArray)jso.get("resOrderDeliveries");
		if(orderDeliveries!=null&&orderDeliveries.size()>0){
			JSONObject orderDeliver = (JSONObject)orderDeliveries.get(0);
			String deliveryPerson =dealString( orderDeliver.get("deliveryPerson")); //骑手姓名
			String deliveryPhone =dealString( orderDeliver.get("deliveryPhone"));	//骑手电话
			setDeliveryPerson(deliveryPerson);
			setDeliveryPhone(deliveryPhone);
		}
		
		setTotalSubAmount(dealString(jso.get("subAmount")));//优惠金额
		JSONArray favList = (JSONArray)jso.get("orderFavList");
		StringBuffer favDetailText = new StringBuffer();
		for(int i =0; i< favList.size();i++){
			JSONObject fav = (JSONObject)favList.get(i);
			favDetailText.append(dealString(fav.get("favName")));
			if(i<favList.size()-1){
				favDetailText.append(",");
			}
			
		}
		if(favList.size()>0){
			setFavDetail(favDetailText.toString());  							//优惠明细
		}
		else{
			setFavDetail("");
		}
		setTotalPayAmount(dealString(jso.get("payAmount")));//实际付款
		setCommissionAmount(dealString(jso.get("commissionAmount"))); //佣金金额
		setCommissionProportion(dealString(jso.get("commissionProportion"))); //佣金比例
		setBusinessFavourableAmount(dealString(jso.get("businessFavourableAmount"))); //商户平摊优惠
		setPlatformFavourableAmount(dealString(jso.get("platformFavourableAmount"))); //平台平摊优惠
		setDeliverymanSign(dealString("骑手签名：___________________"));// 骑手签名
		setSplitOrderComments(dealString(""));// 打印大订单 拆单备注
		setNeedAccount(dealString(jso.get("needAccount")));// 是否是结账订单
		setHotlinephone(dealString("168"));// 外卖电话
		setOrderSeq(dealString(jso.get("orderNumber")));// 订单流水号
		setEstimatedDeliveryFlag(dealString(""));//预处理标志
		setRestUnitePrintContent(dealString(""));//餐厅联打印内容
		setMultiUnitePrintContent(dealString(""));//多联打印内容
		setStatusId(dealString(""));//状态ID?
		setHeaderMsg(dealString(""));//头部信息？
		setProcessingTime1(dealString(""));//未知信息？
		//解析订单明细
		//Object productValue = jso.get("productMap");
		//System.out.println(productValue);
		//JSONObject jsProduct = (JSONObject) JSONValue.parse(productValue.toString());
											  
		JSONArray array = (JSONArray) jso.get("resTakeOutOrderItemList");
		Iterator it = array.iterator();
		List list = new ArrayList();
		while (it.hasNext()) {
			Map pMap = (Map) it.next();
			list.add(pMap);
		}
		setProductList(list);
		
	}
	
	
	/*
	 * 解析json字符串，提取出相应的值，封装成一个对象返回
	 */
	public static PrintOrderInfo jsonToObj(String json){
		PrintOrderInfo orderInfo = new PrintOrderInfo();
		//测试json字符串
		json="{\"orderFavList\":[],\"crtDttm\":1446170197000,\"crtDttmBefore\":null,\"crtDttmAfter\":null,\"crtUser\":\"Null\",\"lastUpdateDttm\":1446170197000,\"lastUpdateUser\":\"Null\",\"enableFlag\":true,\"version\":0,\"crtUserName\":null,\"lastUpdateUserName\":null,\"id\":\"4028895450b66c720150b675fd9e0001\",\"originId\":\"gh_8710cc5084f4\",\"shopId\":\"b008bbdc-716d-4052-867c-61548fdbce63\",\"shopName\":null,\"orderId\":\"0467170014109463\",\"systemId\":null,\"takeAwaySystemId\":\"1051007\",\"userName\":\"安靖先生\",\"orderPhone\":\"15996262422\",\"city\":\"上海市\",\"address\":\"上海\",\"sendToTime\":null,\"tableFor\":1,\"dineType\":{\"stringValue\":\"TAKEOUT\",\"text\":\"外送\"},\"orderTime\":1446170197000,\"orderDate\":\"2015-10-30\",\"isReserved\":false,\"invoice\":{\"stringValue\":\"NONE\",\"text\":\"不需要发票\"},\"invoiceTitle\":\"\",\"comment\":\"\",\"orderAmount\":56.00,\"subAmount\":0.00,\"payAmount\":56.00,\"dishAmount\":null,\"deliveryAmount\":0.00,\"boxAmount\":0.00,\"dishwareAmount\":0.00,\"usePoints\":false,\"points\":0,\"paymentType\":{\"stringValue\":\"GOODACHIEVE\",\"text\":\"货到付款\"},\"payment\":null,\"paymentMethodNew\":{\"stringValue\":\"CASH\",\"text\":\"现金支付\"},\"paymentStatus\":{\"stringValue\":\"NONPAYMENT\",\"text\":\"未付款\"},\"paymentTime\":null,\"takeOutOrderStatus\":{\"stringValue\":\"UNCONFIRMED\",\"text\":\"订单已受理\"},\"statusComment\":null,\"deliveryStatus\":null,\"deliveryStatusComment\":null,\"statusTime\":null,\"resTakeOutOrderItemList\":[{\"crtDttm\":1446170197000,\"crtDttmBefore\":null,\"crtDttmAfter\":null,\"crtUser\":\"Null\",\"lastUpdateDttm\":1446170197000,\"lastUpdateUser\":\"Null\",\"enableFlag\":true,\"version\":0,\"crtUserName\":null,\"lastUpdateUserName\":null,\"id\":\"4028895450b66c720150b675fd9e0003\",\"originId\":\"gh_8710cc5084f4\",\"orderItemCode\":null,\"menuItemCode\":null,\"productCode\":null,\"productno\":null,\"userCode\":null,\"deviceCode\":null,\"productId\":\"9efd3e7b-4c41-4f21-86ca-bfe1f4f61122\",\"orderItemName\":\"韩式酱猪肉\",\"productType\":null,\"quantity\":1,\"unitPrice\":56.00,\"netUnitPrice\":null,\"amount\":56.00,\"subAmount\":0.00,\"payAmount\":56.00,\"points\":0,\"seq\":0,\"modifyDate\":null,\"crtDttmString\":\"2015-10-30 09:56:37\",\"lastUpdateDttmString\":\"2015-10-30 09:56:37\"}],\"interfaceStatus\":{\"stringValue\":\"SUBMIT_ING\",\"text\":\"正在提交\"},\"shopCartJson\":null,\"shopCartNewJson\":null,\"shopCartDelJson\":null,\"errorMsg\":null,\"st\":null,\"isFrom\":false,\"lat\":null,\"lng\":null,\"synSerialNo\":null,\"statusDescription\":null,\"brandId\":null,\"brandName\":null,\"deliveryUpdateTime\":null,\"updateTime\":null,\"channelId\":\"1051007\",\"channelName\":\"微信餐厅外卖\",\"companyCode\":null,\"optStatus\":null,\"optMessage\":null,\"ipAddress\":null,\"prepayId\":null,\"authCode\":null,\"transactionId\":null,\"takeOutEmpUserCode\":null,\"paymentMethodMd\":null,\"addResTakeOutOrderItemList\":[],\"upResTakeOutOrderItemList\":[],\"delResTakeOutOrderItemList\":[],\"totalAmount\":56.00,\"orderStatuText\":\"待确认\",\"totalPayAmount\":56.00,\"totalSubAmount\":0.00,\"orderName\":\"韩式酱猪肉\",\"actualTotalPriceCents\":\"5600\",\"orderTimeStr\":\"2015-10-30 09:56:37\",\"timeName\":\"更新时间\",\"orderStatusText2\":\"订单已受理\",\"orderColor\":\"deskGreen\",\"updateTimeStr\":null,\"totalAndDeliveryAmount\":56.00,\"crtDttmString\":\"2015-10-30 09:56:37\",\"lastUpdateDttmString\":\"2015-10-30 09:56:37\"}";
		orderInfo.parseJson(json);
		return orderInfo;
	}
	/**
	 * 对json对象作非null判断，若为null，则返回空字符串
	 */
	public static String dealString(Object obj){
		if(obj != null){
			return obj.toString();
		}else{
			return " ";
		}
	}
	//测试json字符串提取
	public static void main(String[] args) {
		String json="{\"orderFavList\":[],\"crtDttm\":1446170197000,\"crtDttmBefore\":null,\"crtDttmAfter\":null,\"crtUser\":\"Null\",\"lastUpdateDttm\":1446170197000,\"lastUpdateUser\":\"Null\",\"enableFlag\":true,\"version\":0,\"crtUserName\":null,\"lastUpdateUserName\":null,\"id\":\"4028895450b66c720150b675fd9e0001\",\"originId\":\"gh_8710cc5084f4\",\"shopId\":\"b008bbdc-716d-4052-867c-61548fdbce63\",\"shopName\":null,\"orderId\":\"0467170014109463\",\"systemId\":null,\"takeAwaySystemId\":\"1051007\",\"userName\":\"安靖先生\",\"orderPhone\":\"15996262422\",\"city\":\"上海市\",\"address\":\"上海\",\"sendToTime\":null,\"tableFor\":1,\"dineType\":{\"stringValue\":\"TAKEOUT\",\"text\":\"外送\"},\"orderTime\":1446170197000,\"orderDate\":\"2015-10-30\",\"isReserved\":false,\"invoice\":{\"stringValue\":\"NONE\",\"text\":\"不需要发票\"},\"invoiceTitle\":\"\",\"comment\":\"\",\"orderAmount\":56.00,\"subAmount\":0.00,\"payAmount\":56.00,\"dishAmount\":null,\"deliveryAmount\":0.00,\"boxAmount\":0.00,\"dishwareAmount\":0.00,\"usePoints\":false,\"points\":0,\"paymentType\":{\"stringValue\":\"GOODACHIEVE\",\"text\":\"货到付款\"},\"payment\":null,\"paymentMethodNew\":{\"stringValue\":\"CASH\",\"text\":\"现金支付\"},\"paymentStatus\":{\"stringValue\":\"NONPAYMENT\",\"text\":\"未付款\"},\"paymentTime\":null,\"takeOutOrderStatus\":{\"stringValue\":\"UNCONFIRMED\",\"text\":\"订单已受理\"},\"statusComment\":null,\"deliveryStatus\":null,\"deliveryStatusComment\":null,\"statusTime\":null,\"resTakeOutOrderItemList\":[{\"crtDttm\":1446170197000,\"crtDttmBefore\":null,\"crtDttmAfter\":null,\"crtUser\":\"Null\",\"lastUpdateDttm\":1446170197000,\"lastUpdateUser\":\"Null\",\"enableFlag\":true,\"version\":0,\"crtUserName\":null,\"lastUpdateUserName\":null,\"id\":\"4028895450b66c720150b675fd9e0003\",\"originId\":\"gh_8710cc5084f4\",\"orderItemCode\":null,\"menuItemCode\":null,\"productCode\":null,\"productno\":null,\"userCode\":null,\"deviceCode\":null,\"productId\":\"9efd3e7b-4c41-4f21-86ca-bfe1f4f61122\",\"orderItemName\":\"韩式酱猪肉\",\"productType\":null,\"quantity\":1,\"unitPrice\":56.00,\"netUnitPrice\":null,\"amount\":56.00,\"subAmount\":0.00,\"payAmount\":56.00,\"points\":0,\"seq\":0,\"modifyDate\":null,\"crtDttmString\":\"2015-10-30 09:56:37\",\"lastUpdateDttmString\":\"2015-10-30 09:56:37\"}],\"interfaceStatus\":{\"stringValue\":\"SUBMIT_ING\",\"text\":\"正在提交\"},\"shopCartJson\":null,\"shopCartNewJson\":null,\"shopCartDelJson\":null,\"errorMsg\":null,\"st\":null,\"isFrom\":false,\"lat\":null,\"lng\":null,\"synSerialNo\":null,\"statusDescription\":null,\"brandId\":null,\"brandName\":null,\"deliveryUpdateTime\":null,\"updateTime\":null,\"channelId\":\"1051007\",\"channelName\":\"微信餐厅外卖\",\"companyCode\":null,\"optStatus\":null,\"optMessage\":null,\"ipAddress\":null,\"prepayId\":null,\"authCode\":null,\"transactionId\":null,\"takeOutEmpUserCode\":null,\"paymentMethodMd\":null,\"addResTakeOutOrderItemList\":[],\"upResTakeOutOrderItemList\":[],\"delResTakeOutOrderItemList\":[],\"totalAmount\":56.00,\"orderStatuText\":\"待确认\",\"totalPayAmount\":56.00,\"totalSubAmount\":0.00,\"orderName\":\"韩式酱猪肉\",\"actualTotalPriceCents\":\"5600\",\"orderTimeStr\":\"2015-10-30 09:56:37\",\"timeName\":\"更新时间\",\"orderStatusText2\":\"订单已受理\",\"orderColor\":\"deskGreen\",\"updateTimeStr\":null,\"totalAndDeliveryAmount\":56.00,\"crtDttmString\":\"2015-10-30 09:56:37\",\"lastUpdateDttmString\":\"2015-10-30 09:56:37\"}";
		PrintOrderInfo orderInfo = new PrintOrderInfo();
		orderInfo.parseJson(json);
		//orderInfo.getOrderItems(orderInfo);
		System.out.println(orderInfo);
	}
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getTransportTime() {
		return transportTime;
	}

	public void setTransportTime(String transportTime) {
		this.transportTime = transportTime;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
	public String getLastUpDateTime() {
		return lastUpDateTime;
	}

	public void setLastUpDateTime(String lastUpDateTime) {
		this.lastUpDateTime = lastUpDateTime;
	}

	public String getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public String getEnumStyleName() {
		return enumStyleName;
	}

    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

	public void setEnumStyleName(String enumStyleName) {
		this.enumStyleName = enumStyleName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getConfirmPhone() {
		return confirmPhone;
	}

	public void setConfirmPhone(String confirmPhone) {
		this.confirmPhone = confirmPhone;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryAddressComments() {
		return deliveryAddressComments;
	}

	public void setDeliveryAddressComments(String deliveryAddressComments) {
		this.deliveryAddressComments = deliveryAddressComments;
	}

	public String getNeedTableware() {
		return needTableware;
	}

	public void setNeedTableware(String needTableware) {
		this.needTableware = needTableware;
	}
	
	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceHeader() {
		return invoiceHeader;
	}

	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	public String getUserComments() {
		return userComments;
	}

	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	public String getStatusComment() {
		return statusComment;
	}

	public void setStatusComment(String statusComment) {
		this.statusComment = statusComment;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getDeliverymanSign() {
		return deliverymanSign;
	}

	public void setDeliverymanSign(String deliverymanSign) {
		this.deliverymanSign = deliverymanSign;
	}

	public String getSplitOrderComments() {
		return splitOrderComments;
	}

	public void setSplitOrderComments(String splitOrderComments) {
		this.splitOrderComments = splitOrderComments;
	}

	public String getNeedAccount() {
		return needAccount;
	}

	public void setNeedAccount(String needAccount) {
		this.needAccount = needAccount;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getHotlinephone() {
		return hotlinephone;
	}

	public void setHotlinephone(String hotlinephone) {
		this.hotlinephone = hotlinephone;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getEstimatedDeliveryFlag() {
		return estimatedDeliveryFlag;
	}

	public void setEstimatedDeliveryFlag(String estimatedDeliveryFlag) {
		this.estimatedDeliveryFlag = estimatedDeliveryFlag;
	}

	public Map getProductMap() {
		return productMap;
	}

	public void setProductMap(Map productMap) {
		this.productMap = productMap;
	}

	public String getHeaderDes() {
		return headerDes;
	}

	public void setHeaderDes(String headerDes) {
		this.headerDes = headerDes;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List getProductList() {
		return productList;
	}

	public void setProductList(List productList) {
		this.productList = productList;
	}

	public String getTotalProductQuantity() {
		return totalProductQuantity;
	}

	public void setTotalProductQuantity(String totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
	}

	public String getBusinessAmount() {
		return businessAmount;
	}

	public void setBusinessAmount(String businessAmount) {
		this.businessAmount = businessAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	

	
	
	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public String getBoxAmount() {
		return boxAmount;
	}

	public void setBoxAmount(String boxAmount) {
		this.boxAmount = boxAmount;
	}

	public String getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(String deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public String getRestUnitePrintContent() {
		return restUnitePrintContent;
	}

	public void setRestUnitePrintContent(String restUnitePrintContent) {
		this.restUnitePrintContent = restUnitePrintContent;
	}

	public String getMultiUnitePrintContent() {
		return multiUnitePrintContent;
	}

	public void setMultiUnitePrintContent(String multiUnitePrintContent) {
		this.multiUnitePrintContent = multiUnitePrintContent;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getHeaderMsg() {
		return headerMsg;
	}

	public void setHeaderMsg(String headerMsg) {
		this.headerMsg = headerMsg;
	}
	public String getTotalPayAmount() {
		return totalPayAmount;
	}

	public String getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(String commissionAmount) {
		this.commissionAmount = commissionAmount;
	}

	public String getCommissionProportion() {
		return commissionProportion;
	}

	public void setCommissionProportion(String commissionProportion) {
		this.commissionProportion = commissionProportion;
	}

	public String getBusinessFavourableAmount() {
		return businessFavourableAmount;
	}

	public void setBusinessFavourableAmount(String businessFavourableAmount) {
		this.businessFavourableAmount = businessFavourableAmount;
	}

	public String getPlatformFavourableAmount() {
		return platformFavourableAmount;
	}

	public void setPlatformFavourableAmount(String platformFavourableAmount) {
		this.platformFavourableAmount = platformFavourableAmount;
	}

	public void setTotalPayAmount(String totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}

	public String getTotalSubAmount() {
		return totalSubAmount;
	}

	public void setTotalSubAmount(String totalSubAmount) {
		this.totalSubAmount = totalSubAmount;
	}

	public String getFavDetail() {
		return favDetail;
	}

	public void setFavDetail(String favDetail) {
		this.favDetail = favDetail;
	}

	
}


