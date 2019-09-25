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
}
