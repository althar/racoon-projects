package com.octys.rzd;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class NumericDialog extends DialogLZD implements Serializable {

	public interface Validator {
		boolean isValid(String s);
	}

	public static final int ARG1_CANCEL = 0;
	public static final int ARG1_OK = 1;
	private static final long serialVersionUID = 1L;

	private static final String INSTANCE_STATE_NUMBER_ID = "NUM";

	private static final int buttonId[] = { R.id.n0, R.id.n1, R.id.n2, R.id.n3, R.id.n4, R.id.n5, R.id.n6, R.id.n7, R.id.n8, R.id.n9,
			R.id.backspace, R.id.ok };

	private static final int numericButtonId[] = { R.id.n0, R.id.n1, R.id.n2, R.id.n3, R.id.n4, R.id.n5, R.id.n6, R.id.n7, R.id.n8, R.id.n9 };

	private String name;
	private int minLen, maxLen;
	private Button okButton, bsButton;
	private EditNumber number;
	private TextView validLabel;

	private Validator validator = null;
	private Context context;

	public NumericDialog(TicketScannerActivity context, String name, int minLen, int maxLen) {
		super(context, R.style.NumericDialogTheme);
		this.context = context;
		this.name = name;
		this.minLen = minLen;
		this.maxLen = maxLen;
	}

	public NumericDialog(TicketScannerActivity context, String name, int minLen, int maxLen, String val, Validator validator) {
		this(context, name, minLen, maxLen);
		this.validator = validator;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.numeric);
		((TextView) findViewById(R.id.msg)).setText(name);
		for (int id : buttonId) {
			View v = findViewById(id);
			v.setOnClickListener(listener);
		}

		findViewById(R.id.backspace).setOnLongClickListener(long_listener);
		number = (EditNumber) findViewById(R.id.number);
		okButton = (Button) findViewById(R.id.ok);
		bsButton = (Button) findViewById(R.id.backspace);
		validLabel = (TextView) findViewById(R.id.label_valid);
		updateButtons();
	}

    @Override
    public void onPrepare() {
        super.onPrepare();
        updateButtons();
    }

    public void setText(String text) {
        number.setText(text==null ? "" : text);
    }

	private View.OnLongClickListener long_listener = new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			backspace();
			return false;
		}
	};

	private View.OnClickListener listener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.ok) {
				onComplete(String.valueOf(number.getText()));
				number.setText("");
				NumericDialog.this.dismiss();
			} else if (v.getId() == R.id.backspace) {
				backspace();
			} else if (number.getText().length() < maxLen) {
				number.append(((Button) v).getText());
				updateButtons();
			}
		}

	};

	public void onComplete(String number) {
	}

	private void updateButtons() {
		String s = number.getText().toString();
		int len = s.length();
		boolean valid = validator == null || validator.isValid(s);
		okButton.setEnabled(len >= minLen && valid);
		bsButton.setEnabled(len > 0);
		for (int buttonId : numericButtonId) {
			((Button) findViewById(buttonId)).setEnabled(len < maxLen);
		}
		if (len < minLen)
			validLabel.setText(context.getString(R.string.numeric_dialog_short));
		else if (!valid)
			validLabel.setText(context.getString(R.string.numeric_dialog_invalid));
		else
			validLabel.setText("");
	}

	private void backspace() {
		CharSequence fieldText = number.getText();
		if (fieldText.length() > 0) {
			number.setText(fieldText.subSequence(0, fieldText.length() - 1));
			updateButtons();
		}
	}

	@Override
	public Bundle onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putString(INSTANCE_STATE_NUMBER_ID, number.getText().toString());
		bundle.putAll(super.onSaveInstanceState());
		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Bundle state) {
		number.setText(state.getString(INSTANCE_STATE_NUMBER_ID));
		super.onRestoreInstanceState(state);
		updateButtons();
	}
}
