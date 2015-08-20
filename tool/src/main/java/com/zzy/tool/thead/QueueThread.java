package com.zzy.tool.thead;

public class QueueThread extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		for(int i=0;i<10000000;i++){
			System.out.println(i);
		}
		System.out.println("22222222");
	}

}
