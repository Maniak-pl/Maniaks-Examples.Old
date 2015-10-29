package pl.maniak.appexample.view;

import java.util.List;

public interface PinPatternInterface {

    public void clearPattern();

    public List<Integer> getPattern();

    public void setRingColor(int color);

    public void setPatternListener(PatternListener listener);

    public boolean isInputEnabled();

    public void setInputEnabled(boolean mIsInputEnabled);

    public int getPatternType();

    public void setPatternType(int mPatternType);

    public interface PatternListener{

        public void onPatternStarted();

        public void onPatternEntered(List<Integer> pattern);

        public void onPatternCleared();
    }
}
