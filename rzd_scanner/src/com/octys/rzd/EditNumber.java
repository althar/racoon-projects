package com.octys.rzd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class EditNumber extends EditText {
	public EditNumber(Context context, AttributeSet as) {
		super(context, as);
	}

	@Override
	public boolean onCheckIsTextEditor() {
		return false;
	}

}    
