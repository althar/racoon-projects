package com.octys.rzd;

import java.util.regex.Pattern;

public class Inspector {

	public static boolean all_digits(String num) {
		int n = num.length();
		if (n==0) return false;
		do 
			if (!Character.isDigit(num.charAt(n-1))) return false;
		while (--n>0);
		return true;
	}
	
	public static boolean mooncheck(String num) {
		int n = num.length();
		int sum=0;
		if (n>0) {
			for (int i=0; i<n; i++) {
				int p = Character.getNumericValue(num.charAt(n-i-1));
				if (p<0 || p>9) return false;
				if (i % 2!=0) {
					p=2*p;
					if (p>9) p-=9;
				}
				sum+=p;
			}	
			return (sum%10==0);
		} else return false;	
	}
	
	public static boolean rr_valid(String rr) {
		if (rr==null) return false;
		int l = rr.length();
		if (l< Consts.RAILROAD_TICKET_NUMBER_LENGTH_MIN ||
			l> Consts.RAILROAD_TICKET_NUMBER_LENGTH_MAX) return false;
		return all_digits(rr);
	}

	public static boolean lt_valid(String lt, Pattern regexp) {
		if (lt==null) return false;
		int l = lt.length();
		if (l< Consts.LOTTERY_TICKET_NUMBER_LENGTH_MIN ||
			l> Consts.LOTTERY_TICKET_NUMBER_LENGTH_MAX) return false;
		if (!all_digits(lt)) return false;
		if (regexp!=null && !regexp.matcher(lt).matches()) return false; 
		return mooncheck(lt);
	}
	
}

/* original code

function mooncheck( $num ) {
	$n = strlen( $num );
	if ( $n > 0 ) {
		for ( $i=0; $i <= $n; $i++ ) {
			$p = $num[$n-$i-1];
			if ( $i % 2 != 0 ) {
				$p = 2 * $p;
				if ( $p > 9 ) {
					$p = $p - 9;
				}
			}
			$sum = $sum + $p;
		}
		if ( $sum % 10 == 0 ) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

 */
