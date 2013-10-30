package com.octys.rzd;

public class Mooncheck {

	
	public static boolean valid(String num) {
		int n = num.length();
		int sum=0;
		if (n>0) {
			for (int i=0; i<n; i++) {
				int p = Character.getNumericValue(num.charAt(n-i-1));
				if (p<0) return false;
				if (i % 2!=0) {
					p=2*p;
					if (p>9) p-=9;
				}
				sum+=p;
			}	
			return (sum%10==0);
		} else return false;	
	}
	
}

/*

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
