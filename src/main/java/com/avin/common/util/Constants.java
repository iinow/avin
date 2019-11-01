package com.avin.common.util;

public class Constants {
	public enum PROVIDER {
		GOOGLE(1), 
		FACEBOOK(2), 
		KAKAO(3), 
		GITHUB(4);
		
		public final int value;
		
		PROVIDER(int value) {
			this.value = value;
		}
		
		public static PROVIDER findPROVIDER(int value) {
			for(PROVIDER provider: PROVIDER.values()) {
				if(provider.value == value) {
					return provider;
				}
			}
			throw new RuntimeException();
		}
	}
	
	public static final String BOARD_HUMOR = "humor";
	
	public enum BOARDTYPE{
		HUMOR(1);
		
		public final int value;
		
		BOARDTYPE(int value){
			this.value = value;
		}
		
		public static BOARDTYPE findBOARDTYPE(int value) {
			for(BOARDTYPE type: values()) {
				if(type.value == value) {
					return type;
				}
			}
			throw new RuntimeException();
		}
	}
}
