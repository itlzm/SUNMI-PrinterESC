package com.example.testprinteresc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrinterUtil {

	public static final byte ESC = 27;// 换码
	public static final byte FS = 28;// 文本分隔符
	public static final byte GS = 29;// 组分隔符
	public static final byte DLE = 16;// 数据连接换码
	public static final byte EOT = 4;// 传输结束
	public static final byte ENQ = 5;// 询问字符
	public static final byte SP = 32;// 空格
	public static final byte HT = 9;// 横向列表
	public static final byte LF = 10;// 打印并换行（水平定位）
	public static final byte CR = 13;// 归位键
	public static final byte FF = 12;// 走纸控制（打印并回到标准模式（在页模式下） ）
	public static final byte CAN = 24;// 作废（页模式下取消打印数据 ）

	// ------------------------打印机初始化-----------------------------

	/**
	 * 打印机初始化
	 * 
	 * @return
	 */
	public static byte[] init_printer() {
		byte[] result = new byte[2];
		result[0] = ESC;
		result[1] = 64;
		return result;
	}

	// ------------------------换行-----------------------------

	/**
	 * 换行
	 * 
	 * @param lineNum要换几行
	 * @return
	 */
	public static byte[] nextLine(int lineNum) {
		byte[] result = new byte[lineNum];
		for (int i = 0; i < lineNum; i++) {
			result[i] = LF;
		}

		return result;
	}

	// ------------------------下划线-----------------------------

	/**
	 * 绘制下划线（1点宽）
	 * 
	 * @return
	 */
	public static byte[] underlineWithOneDotWidthOn() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 45;
		result[2] = 1;
		return result;
	}

	/**
	 * 绘制下划线（2点宽）
	 * 
	 * @return
	 */
	public static byte[] underlineWithTwoDotWidthOn() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 45;
		result[2] = 2;
		return result;
	}

	/**
	 * 取消绘制下划线
	 * 
	 * @return
	 */
	public static byte[] underlineOff() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 45;
		result[2] = 0;
		return result;
	}

	// ------------------------加粗-----------------------------

	/**
	 * 选择加粗模式
	 * 
	 * @return
	 */
	public static byte[] boldOn() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 69;
		result[2] = 0xF;
		return result;
	}

	/**
	 * 取消加粗模式
	 * 
	 * @return
	 */
	public static byte[] boldOff() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 69;
		result[2] = 0;
		return result;
	}

	// ------------------------对齐-----------------------------

	/**
	 * 左对齐
	 * 
	 * @return
	 */
	public static byte[] alignLeft() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 97;
		result[2] = 0;
		return result;
	}

	/**
	 * 居中对齐
	 * 
	 * @return
	 */
	public static byte[] alignCenter() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 97;
		result[2] = 1;
		return result;
	}

	/**
	 * 右对齐
	 * 
	 * @return
	 */
	public static byte[] alignRight() {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 97;
		result[2] = 2;
		return result;
	}

	/**
	 * 水平方向向右移动col列
	 * 
	 * @param col
	 * @return
	 */
	public static byte[] set_HT_position(byte col) {
		byte[] result = new byte[4];
		result[0] = ESC;
		result[1] = 68;
		result[2] = col;
		result[3] = 0;
		return result;
	}
	// ------------------------字体变大-----------------------------

	/**
	 * 字体变大为标准的n倍
	 * 
	 * @param num
	 * @return
	 */
	public static byte[] fontSizeSetBig(int num) {
		byte realSize = 0;
		switch (num) {
		case 1:
			realSize = 0;
			break;
		case 2:
			realSize = 17;
			break;
		case 3:
			realSize = 34;
			break;
		case 4:
			realSize = 51;
			break;
		case 5:
			realSize = 68;
			break;
		case 6:
			realSize = 85;
			break;
		case 7:
			realSize = 102;
			break;
		case 8:
			realSize = 119;
			break;
		//纵向放大2倍
		case 9:
			realSize = 1;
			break;
			//纵向放大2倍
		case 10:
			realSize = 16;
			break;
		}
		byte[] result = new byte[3];
		result[0] = 29;
		result[1] = 33;
		result[2] = realSize;
		return result;
	}

	// ------------------------字体变小-----------------------------

	/**
	 * 字体取消倍宽倍高
	 * 
	 * @param num
	 * @return
	 */
	public static byte[] fontSizeSetSmall(int num) {
		byte[] result = new byte[3];
		result[0] = ESC;
		result[1] = 33;

		return result;
	}

	// ------------------------切纸-----------------------------

	/**
	 * 进纸并全部切割
	 * 
	 * @return
	 */
	public static byte[] feedPaperCutAll() {
		byte[] result = new byte[4];
		result[0] = GS;
		result[1] = 86;
		result[2] = 65;
		result[3] = 0;
		return result;
	}

	/**
	 * 进纸并切割（左边留一点不切）
	 * 
	 * @return
	 */
	public static byte[] feedPaperCutPartial() {
		byte[] result = new byte[4];
		result[0] = GS;
		result[1] = 86;
		result[2] = 66;
		result[3] = 0;
		return result;
	}

	// ------------------------切纸-----------------------------
	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	public static byte[] byteMerger(byte[][] byteList) {

		int length = 0;
		for (int i = 0; i < byteList.length; i++) {
			length += byteList[i].length;
		}
		byte[] result = new byte[length];

		int index = 0;
		for (int i = 0; i < byteList.length; i++) {
			byte[] nowByte = byteList[i];
			for (int k = 0; k < byteList[i].length; k++) {
				result[index] = nowByte[k];
				index++;
			}
		}
		for (int i = 0; i < index; i++) {
			// CommonUtils.LogWuwei("", "result[" + i + "] is " + result[i]);
		}
		return result;
	}
	
	//设置字体大小，换行的常量
	static byte[] nextLine = PrinterUtil.nextLine(1);
	static byte[] next2Line = PrinterUtil.nextLine(2);
	static byte[] next4Line = PrinterUtil.nextLine(4);
	static byte[] center = PrinterUtil.alignCenter();
	static byte[] left = PrinterUtil.alignLeft();
	static byte[] fontSize1Big = PrinterUtil.fontSizeSetBig(2);
	static byte[] fontSize2Big = PrinterUtil.fontSizeSetBig(3);
	static byte[] fontSize1Small = PrinterUtil.fontSizeSetSmall(2);
	static byte[] fontSize2Small = PrinterUtil.fontSizeSetSmall(3);
	static byte[] fontSize2Y = PrinterUtil.fontSizeSetBig(9);
	static byte[] fontSize2X = PrinterUtil.fontSizeSetBig(10);
	static byte[] breakPartial = PrinterUtil.feedPaperCutPartial();
	static byte[] boldOn = PrinterUtil.boldOn();
	static byte[] boldOff = PrinterUtil.boldOff();
	//打印时用到的分界虚线
	static String brokeLine = "-----------------------------";


	// --------------------模拟数据
	public static byte[] generateMockData() {
		
		List<String> strMsgList = new ArrayList<String>();
		strMsgList.add("出餐单（午餐）**万通中心店");
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(boldOn));
		strMsgList.add(new String(fontSize2Big));
		strMsgList.add("网 507");
		strMsgList.add(new String(boldOff));
		strMsgList.add(new String(fontSize2Small));
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(left));
		strMsgList.add("订单编号：11234");
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(boldOn));
		strMsgList.add(new String(fontSize1Big));
		strMsgList.add("韭菜鸡蛋饺子-小份（单）");
		strMsgList.add(new String(boldOff));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(left));
		strMsgList.add(new String(next2Line));
		strMsgList.add("应收:22元 优惠：2.5元 ");
		strMsgList.add(new String(nextLine));
		strMsgList.add("实收:19.5元");
		strMsgList.add(new String(nextLine));
		strMsgList.add("取餐时间:2015-02-13 12:51:59");
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单时间：2015-02-13 12:35:15");
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(center));
		strMsgList.add("微信关注\"**\"自助下单每天免1元");
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add("饭后点评再奖5毛");
		strMsgList.add(new String(next4Line));
		strMsgList.add(new String(breakPartial));
		//调用listToBytes方法，提取集合中每一个元素，得到其GB2312编码格式byte字节数组，再讲该数组放到cmdList的二维数组中
		byte[][] cmdBytes = listToBytes(strMsgList);

		return PrinterUtil.byteMerger(cmdBytes);

	}
	
	/**
	 * 将存储打印信息的strMsgList转换为byte二维数组返回。
	 * @param strMsgList
	 * @return
	 */
	public static byte[][] listToBytes(List<String> strMsgList){
		byte[][] cmdBytes = new byte[strMsgList.size()][];
		for (int i = 0; i < strMsgList.size(); i++) {
			
			try {
				cmdBytes[i] = strMsgList.get(i).getBytes("gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return cmdBytes;
	}

	
	/**
	 * 打印餐厅联数据
	 * 
	 * @return
	 */
	public static byte[] generateRestaurantData() {
		// json为模拟数据，该数据在jsonToObj方法中手动赋值，后续应该由调用者调用该方法时，当做参数传入。
		String json = null;
		PrintOrderInfo printOrderInfo = PrintOrderInfo.jsonToObj(json);
		
		List<String> strMsgList = new ArrayList<String>();
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize2Y));
		strMsgList.add(printOrderInfo.RESTAURANT_COUPLET);
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单渠道：" + printOrderInfo.getChannelName());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(left));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add("餐厅名称 : " + printOrderInfo.getShopName());
		strMsgList.add(new String(nextLine));
		strMsgList.add("订 单 号 : " + printOrderInfo.getOrderId());
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单时间 : " + printOrderInfo.getOrderTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送达时间 : " + printOrderInfo.getLastUpDateTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("佣金金额 ：" + printOrderInfo.getCommissionAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize1Big));
		strMsgList.add(new String("客户信息"));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(left));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add("联 系 人 : " + printOrderInfo.getContactPerson());
		strMsgList.add(new String(nextLine));
		strMsgList.add("联系电话 : " + printOrderInfo.getContactPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("确认电话 : " + printOrderInfo.getConfirmPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("支付方式 : " + printOrderInfo.getPayMethod());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送餐地址 : " + printOrderInfo.getDeliveryAddress());
		strMsgList.add(new String(nextLine));
		strMsgList.add("是否需要餐具 : " + printOrderInfo.getNeedTableware());
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String("品/项				数量				单价				金额"));
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(fontSize2Y));
		Map productMap = (Map) (printOrderInfo.getProductList().get(0));
		strMsgList.add(productMap.get("orderItemName") + "  " + productMap.get("quantity") + "   "
				+ productMap.get("unitPrice") + "    " + productMap.get("amount"));
		strMsgList.add(new String(next2Line));
		strMsgList.add("合计金额                " + printOrderInfo.getTotalAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("实际金额                " + printOrderInfo.getTotalPayAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("配送金额                " + printOrderInfo.getDeliveryAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("餐 盒 费                " + printOrderInfo.getBoxAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("优惠金额                " + printOrderInfo.getTotalSubAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add("(商户 : " + printOrderInfo.getBusinessFavourableAmount() + ",平台 : "
				+ printOrderInfo.getPlatformFavourableAmount() + ")");
		strMsgList.add(new String(next2Line));
		strMsgList.add("商家应收                " + printOrderInfo.getBusinessAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String("骑手签名 :__________________"));
		strMsgList.add(new String(next4Line));
		strMsgList.add(new String(breakPartial));
		
		//调用listToBytes方法，提取集合中每一个元素，得到其GB2312编码格式byte字节数组，再讲该数组放到cmdList的二维数组中
		byte[][] cmdBytes = listToBytes(strMsgList);
		
		return PrinterUtil.byteMerger(cmdBytes);
	}

	/**
	 * 打印骑手联数据
	 * 
	 * @return
	 */
	public static byte[] generateDeliveryManData() {

		// json为模拟数据，该数据在jsonToObj方法中手动赋值，后续应该由调用者调用该方法时，当做参数传入。
		String json = null;
		PrintOrderInfo printOrderInfo = PrintOrderInfo.jsonToObj(json);
		
		List<String> strMsgList = new ArrayList<String>();
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize2Y));
		strMsgList.add(printOrderInfo.DELIVERYMAN_COUPLET);
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单渠道：" + printOrderInfo.getChannelName());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(left));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add("餐厅名称 : " + printOrderInfo.getShopName());
		strMsgList.add(new String(nextLine));
		strMsgList.add("订 单 号 : " + printOrderInfo.getOrderId());
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单时间 : " + printOrderInfo.getOrderTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送达时间 : " + printOrderInfo.getLastUpDateTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("佣金金额 ：" + printOrderInfo.getCommissionAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize1Big));
		strMsgList.add(new String("客户信息"));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(left));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add("联 系 人 : " + printOrderInfo.getContactPerson());
		strMsgList.add(new String(nextLine));
		strMsgList.add("联系电话 : " + printOrderInfo.getContactPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("确认电话 : " + printOrderInfo.getConfirmPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("支付方式 : " + printOrderInfo.getPayMethod());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送餐地址 : " + printOrderInfo.getDeliveryAddress());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("是否需要餐具 : " + printOrderInfo.getNeedTableware());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String("品/项				数量				单价				金额"));
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(fontSize2Y));
		Map productMap = (Map) (printOrderInfo.getProductList().get(0));
		strMsgList.add(productMap.get("orderItemName") + "  " + productMap.get("quantity") + "   "
				+ productMap.get("unitPrice") + "    " + productMap.get("amount"));
		strMsgList.add(new String(next2Line));
		strMsgList.add("合计金额                " + printOrderInfo.getTotalAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("实际金额                " + printOrderInfo.getTotalPayAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("配送金额                " + printOrderInfo.getDeliveryAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("餐 盒 费                " + printOrderInfo.getBoxAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("优惠金额                " + printOrderInfo.getTotalSubAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add("(商户 : " + printOrderInfo.getBusinessFavourableAmount() + ",平台 : "
				+ printOrderInfo.getPlatformFavourableAmount() + ")");
		strMsgList.add(new String(next2Line));
		strMsgList.add("商家应收                " + printOrderInfo.getBusinessAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String("客户签名 :__________________"));
		strMsgList.add(new String(next4Line));
		strMsgList.add(new String(breakPartial));
		
		//调用listToBytes方法，提取集合中每一个元素，得到其GB2312编码格式byte字节数组，再讲该数组放到cmdList的二维数组中
		byte[][] cmdBytes = listToBytes(strMsgList);
		
		return PrinterUtil.byteMerger(cmdBytes);
	}

	/**
	 * 打印顾客联数据
	 * 
	 * @return
	 */
	public static byte[] generateCustomerData() {

		// json为模拟数据，该数据在jsonToObj方法中手动赋值，后续应该由调用者调用该方法时，当做参数传入。
		String json = null;
		PrintOrderInfo printOrderInfo = PrintOrderInfo.jsonToObj(json);
		
		List<String> strMsgList = new ArrayList<String>();
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize2Y));
		strMsgList.add(printOrderInfo.CUSTOMER_COUPLET);
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单渠道：" + printOrderInfo.getChannelName());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(left));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add("餐厅名称 : " + printOrderInfo.getShopName());
		strMsgList.add(new String(nextLine));
		strMsgList.add("订 单 号 : " + printOrderInfo.getOrderId());
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单时间 : " + printOrderInfo.getOrderTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送达时间 : " + printOrderInfo.getLastUpDateTime());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("佣金金额 ：" + printOrderInfo.getCommissionAmount());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize1Big));
		strMsgList.add(new String("客户信息"));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(left));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add("联 系 人 : " + printOrderInfo.getContactPerson());
		strMsgList.add(new String(nextLine));
		strMsgList.add("联系电话 : " + printOrderInfo.getContactPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("确认电话 : " + printOrderInfo.getConfirmPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("支付方式 : " + printOrderInfo.getPayMethod());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送餐地址 : " + printOrderInfo.getDeliveryAddress());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("是否需要餐具 : " + printOrderInfo.getNeedTableware());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String("品/项				数量				单价				金额"));
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(fontSize2Y));
		Map productMap = (Map) (printOrderInfo.getProductList().get(0));
		strMsgList.add(productMap.get("orderItemName") + "  " + productMap.get("quantity") + "   "
				+ productMap.get("unitPrice") + "    " + productMap.get("amount"));
		strMsgList.add(new String(next2Line));
		strMsgList.add("合计金额                " + printOrderInfo.getTotalAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("实际金额                " + printOrderInfo.getTotalPayAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("配送金额                " + printOrderInfo.getDeliveryAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("餐 盒 费                " + printOrderInfo.getBoxAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("优惠金额                " + printOrderInfo.getTotalSubAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add("(商户 : " + printOrderInfo.getBusinessFavourableAmount() + ",平台 : "
				+ printOrderInfo.getPlatformFavourableAmount() + ")");
		strMsgList.add(new String(next2Line));
		strMsgList.add("商家应收                " + printOrderInfo.getBusinessAmount());
//		strMsgList.add(new String(next2Line));
//		strMsgList.add(new String("客户签名 :__________________"));
		strMsgList.add(new String(next4Line));
		strMsgList.add(new String(breakPartial));
		
		//调用listToBytes方法，提取集合中每一个元素，得到其GB2312编码格式byte字节数组，再讲该数组放到cmdList的二维数组中
		byte[][] cmdBytes = listToBytes(strMsgList);
		
		return PrinterUtil.byteMerger(cmdBytes);
	}

	/**
	 * 打印取消联数据
	 * 
	 * @return
	 */
	public static byte[] generateOrderCancleData() {

		// json为模拟数据，该数据在jsonToObj方法中手动赋值，后续应该由调用者调用该方法时，当做参数传入。
		String json = null;
		PrintOrderInfo printOrderInfo = PrintOrderInfo.jsonToObj(json);
		
		List<String> strMsgList = new ArrayList<String>();
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize2Y));
		strMsgList.add(printOrderInfo.ORDER_CANCEL);
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单渠道：" + printOrderInfo.getChannelName());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(left));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add("餐厅名称 : " + printOrderInfo.getShopName());
		strMsgList.add(new String(nextLine));
		strMsgList.add("订 单 号 : " + printOrderInfo.getOrderId());
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单时间 : " + printOrderInfo.getOrderTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送达时间 : " + printOrderInfo.getLastUpDateTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("佣金比例 : " + printOrderInfo.getCommissionProportion());
		strMsgList.add(new String(nextLine));
		strMsgList.add("取消原因 : " + printOrderInfo.getStatusComment());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("佣金金额 ：" + printOrderInfo.getCommissionAmount());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize1Big));
		strMsgList.add(new String("客户信息"));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(left));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add("联 系 人 : " + printOrderInfo.getContactPerson());
		strMsgList.add(new String(nextLine));
		strMsgList.add("联系电话 : " + printOrderInfo.getContactPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("确认电话 : " + printOrderInfo.getConfirmPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("支付方式 : " + printOrderInfo.getPayMethod());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送餐地址 : " + printOrderInfo.getDeliveryAddress());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("是否需要餐具 : " + printOrderInfo.getNeedTableware());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String("品/项				数量				单价				金额"));
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(fontSize2Y));
		Map productMap = (Map) (printOrderInfo.getProductList().get(0));
		strMsgList.add(productMap.get("orderItemName") + "  " + productMap.get("quantity") + "   "
				+ productMap.get("unitPrice") + "    " + productMap.get("amount"));
		strMsgList.add(new String(next2Line));
		strMsgList.add("合计金额                " + printOrderInfo.getTotalAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("实际金额                " + printOrderInfo.getTotalPayAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("配送金额                " + printOrderInfo.getDeliveryAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("餐 盒 费                " + printOrderInfo.getBoxAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("优惠金额                " + printOrderInfo.getTotalSubAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add("(商户 : " + printOrderInfo.getBusinessFavourableAmount() + ",平台 : "
				+ printOrderInfo.getPlatformFavourableAmount() + ")");
		strMsgList.add(new String(next2Line));
		strMsgList.add("商家应收                " + printOrderInfo.getBusinessAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String("客户签名 :__________________"));
		strMsgList.add(new String(next4Line));
		strMsgList.add(new String(breakPartial));
		
		//调用listToBytes方法，提取集合中每一个元素，得到其GB2312编码格式byte字节数组，再讲该数组放到cmdList的二维数组中
		byte[][] cmdBytes = listToBytes(strMsgList);
		
		return PrinterUtil.byteMerger(cmdBytes);
	}
	/**
	 * 打印退款联数据
	 * @return
	 */
	public static byte[] generateOrderRefoundData() {
		
		// json为模拟数据，该数据在jsonToObj方法中手动赋值，后续应该由调用者调用该方法时，当做参数传入。
		String json = null;
		PrintOrderInfo printOrderInfo = PrintOrderInfo.jsonToObj(json);
		
		List<String> strMsgList = new ArrayList<String>();
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize2Y));
		strMsgList.add(printOrderInfo.ORDER_REFUND);
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单渠道：" + printOrderInfo.getChannelName());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String(left));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add("餐厅名称 : " + printOrderInfo.getShopName());
		strMsgList.add(new String(nextLine));
		strMsgList.add("订 单 号 : " + printOrderInfo.getOrderId());
		strMsgList.add(new String(nextLine));
		strMsgList.add("下单时间 : " + printOrderInfo.getOrderTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送达时间 : " + printOrderInfo.getLastUpDateTime());
		strMsgList.add(new String(nextLine));
		strMsgList.add("佣金比例 : " + printOrderInfo.getCommissionProportion());
		strMsgList.add(new String(nextLine));
		strMsgList.add("取消原因 : " + printOrderInfo.getStatusComment());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("佣金金额 ：" + printOrderInfo.getCommissionAmount());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(center));
		strMsgList.add(new String(fontSize1Big));
		strMsgList.add(new String("客户信息"));
		strMsgList.add(new String(fontSize1Small));
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(left));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add("联 系 人 : " + printOrderInfo.getContactPerson());
		strMsgList.add(new String(nextLine));
		strMsgList.add("联系电话 : " + printOrderInfo.getContactPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("确认电话 : " + printOrderInfo.getConfirmPhone());
		strMsgList.add(new String(nextLine));
		strMsgList.add("支付方式 : " + printOrderInfo.getPayMethod());
		strMsgList.add(new String(nextLine));
		strMsgList.add("送餐地址 : " + printOrderInfo.getDeliveryAddress());
		strMsgList.add(new String(nextLine));
//		strMsgList.add("是否需要餐具 : " + printOrderInfo.getNeedTableware());
//		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String("品/项				数量				单价				金额"));
		strMsgList.add(new String(nextLine));
		strMsgList.add(brokeLine);
		strMsgList.add(new String(nextLine));
		strMsgList.add(new String(fontSize2Y));
		Map productMap = (Map) (printOrderInfo.getProductList().get(0));
		strMsgList.add(productMap.get("orderItemName") + "  " + productMap.get("quantity") + "   "
				+ productMap.get("unitPrice") + "    " + productMap.get("amount"));
		strMsgList.add(new String(next2Line));
		strMsgList.add("合计金额                " + printOrderInfo.getTotalAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("实际金额                " + printOrderInfo.getTotalPayAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("配送金额                " + printOrderInfo.getDeliveryAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("餐 盒 费                " + printOrderInfo.getBoxAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add("优惠金额                " + printOrderInfo.getTotalSubAmount());
		strMsgList.add(new String(nextLine));
		strMsgList.add("(商户 : " + printOrderInfo.getBusinessFavourableAmount() + ",平台 : "
				+ printOrderInfo.getPlatformFavourableAmount() + ")");
		strMsgList.add(new String(next2Line));
		strMsgList.add("商家应收                " + printOrderInfo.getBusinessAmount());
		strMsgList.add(new String(next2Line));
		strMsgList.add(new String("客户签名 :__________________"));
		strMsgList.add(new String(next4Line));
		strMsgList.add(new String(breakPartial));
		
		//调用listToBytes方法，提取集合中每一个元素，得到其GB2312编码格式byte字节数组，再讲该数组放到cmdList的二维数组中
		byte[][] cmdBytes = listToBytes(strMsgList);
		
		return PrinterUtil.byteMerger(cmdBytes);
	}

}
