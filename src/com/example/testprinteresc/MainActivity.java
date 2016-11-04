package com.example.testprinteresc;

import java.io.IOException;



import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnClickListener{

	BluetoothAdapter mBluetoothAdapter;
	private Button btn, restaurantBtn,deliveryManBtn,customerBtn,orderCancleBtn,orderRefoundBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}
	
	public void initView(){
		btn = (Button) findViewById(R.id.btn);
		restaurantBtn = (Button) findViewById(R.id.restaurantBtn);
		deliveryManBtn = (Button) findViewById(R.id.deliveryManBtn);
		customerBtn = (Button) findViewById(R.id.customerBtn);
		orderCancleBtn = (Button) findViewById(R.id.orderCancleBtn);
		orderRefoundBtn = (Button) findViewById(R.id.orderRefoundBtn);
		
		
		//测试按钮监听点击事件
		btn.setOnClickListener(this);
		//打印餐厅联按钮监听点击事件
		restaurantBtn.setOnClickListener(this);
		//打印骑手联按钮监听点击事件
		deliveryManBtn.setOnClickListener(this);
		//打印顾客联按钮监听点击事件
		customerBtn.setOnClickListener(this);
		//打印取消联按钮监听点击事件
		orderCancleBtn.setOnClickListener(this);
		//打印退款联按钮监听点击事件
		orderRefoundBtn.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn:
			printTest();
			break;
		case R.id.restaurantBtn:
			printRestaurant();
			break;
		case R.id.deliveryManBtn:
			printDeliveryMan();
			break;
		case R.id.customerBtn:
			printCustomer();
			break;
		case R.id.orderCancleBtn:
			printOrderCancle();
			break;
		case R.id.orderRefoundBtn:
			printOrderRefound();
			break;
		default:
			break;
		}
		
	}


	//测试打印demo
	public void printTest() {
		byte[] data = PrinterUtil.generateMockData();
		print(data);
	}
	
	//打印餐厅联
	public void printRestaurant() {
		//生成要打印的餐厅联data
		byte[] data = PrinterUtil.generateRestaurantData();
		print(data);
	}
	//打印骑手联
	public void printDeliveryMan() {
		//生成要打印的骑手联data
		byte[] data = PrinterUtil.generateDeliveryManData();
		print(data);
	}
	//打印顾客联
	public void printCustomer() {
		//生成要打印的顾客联data
		byte[] data = PrinterUtil.generateCustomerData();
		print(data);
	}
	//打印取消联
	public void printOrderCancle() {
		//生成要打印的顾客联data
		byte[] data = PrinterUtil.generateOrderCancleData();
		print(data);
	}
	//打印退款联
	public void printOrderRefound() {
		//生成要打印的顾客联data
		byte[] data = PrinterUtil.generateOrderRefoundData();
		print(data);
	}
	
	/**
	 * 将数据交给打印机打印(如：餐厅联数据，顾客联数据，骑手联数据等)
	 * @param data
	 */
	public void print(byte[] data){
		// 1: Get BluetoothAdapter
		BluetoothAdapter btAdapter = BluetoothUtil.getBTAdapter();
		if (btAdapter == null) {
			Toast.makeText(getBaseContext(), "Please Open Bluetooth!", Toast.LENGTH_LONG).show();
			return;
		}
		// 2: Get Sunmi's InnerPrinter BluetoothDevice
		BluetoothDevice device = BluetoothUtil.getDevice(btAdapter);
		if (device == null) {
			Toast.makeText(getBaseContext(), "Please Make Sure Bluetooth have InnterPrinter!",
					Toast.LENGTH_LONG).show();
			return;
		}
		// 3: Generate a order data(注：该data数据，由调用该方法使用者生成指定要打印的类型，例如：餐厅联，顾客联，骑手联等)
		//byte[] data = PrinterUtil.generateRestaurantData();
		// 4: Using InnerPrinter print data
		BluetoothSocket socket = null;
		try {
			socket = BluetoothUtil.getSocket(device);
			BluetoothUtil.sendData(data, socket);
		} catch (IOException e) {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
