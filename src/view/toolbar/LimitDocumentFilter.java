package view.toolbar;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimitDocumentFilter extends DocumentFilter{
	private int limit = 70;
	//private String searchRegex = "[A-Za-zćčšđž]{1,20}[\\sA-Za-zćčšđž]{0,20}";
	static int spaces = 0;

    public LimitDocumentFilter() {
    }

    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        int currentLength = fb.getDocument().getLength();
        if(spaces >= 3) {
        	limit = currentLength;
        } else {
        	limit = 70;
        }
        int overLimit = (currentLength + text.length()) - limit - length;
        if (overLimit > 0) {
            text = text.substring(0, text.length() - overLimit);
        }
        
        spaces = spaces + text.replaceAll("[^ ]", "").length();
        
        if (text.length() > 0) {
            super.replace(fb, offset, length, text, attrs); 
        }

    }

}
