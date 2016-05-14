package pl.maniak.appexample.section.help.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import pl.maniak.appexample.R;

public class SystemToast {

    public static final int LENGTH_SHORT = 3000;

    public static final int LENGTH_LONG = 5000;

    public static final Style STYLE_ALERT = new Style(LENGTH_LONG, R.drawable.ic_alert_24dp, R.drawable.toast_border_alert);

    public static final Style STYLE_CONFIRM = new Style(LENGTH_SHORT, R.drawable.ic_confirm_24dp, R.drawable.toast_border_confirm);

    public static final Style STYLE_WARNING = new Style(LENGTH_SHORT, R.drawable.ic_warning_24dp, R.drawable.toast_border_warning);

    public static final Style STYLE_INFO = new Style(LENGTH_SHORT, R.drawable.ic_info_24dp, R.drawable.toast_border_info);

    public static void show(Context context, String text,  Style style) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.system_toast_layout, null);

        layout.setBackground(context.getResources().getDrawable(style.background));

        ImageView image = (ImageView) layout.findViewById(R.id.toast_image);
        image.setImageResource(style.getIcon());

        TextView textV = (TextView) layout.findViewById(R.id.toast_text);
        textV.setText(text);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(style.duration);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
    }

    public static class Style {

        private final int duration;
        private final int background;
        private final int icon;


        public Style(int duration, int iconId, int backgrounId) {
            this.duration = duration;
            this.background = backgrounId;
            this.icon = iconId;

        }

        /**
         * Return the duration in milliseconds.
         */
        public int getDuration() {
            return duration;
        }

        /**
         * Return the resource id of background.
         */
        public int getBackground() {
            return background;
        }

        public int getIcon() {
            return icon;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof SystemToast.Style)) {
                return false;
            }
            Style style = (Style) o;
            return style.duration == duration
                    && style.background == background
                        && style.icon == icon;
        }

    }
}