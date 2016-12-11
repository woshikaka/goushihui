package wd;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.sfmy.gsh.utils.MyArith;


public class Temp {
	public static void main(String[] args) {
		BigDecimal marketPrice = new BigDecimal(2.0)/*.setScale(2)*/;
		BigDecimal price = new BigDecimal(1.1)/*.setScale(2)*/;
		
		System.out.println(MyArith.sub(2.0,1.1));
	}
}


