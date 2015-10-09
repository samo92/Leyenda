import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.appcompat.BuildConfig;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by LEONARDO on 07/10/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class AudioStream {
    String url = "https://www.dropbox.com/s/of9jpsueawnk2vb/Hood%20P%20-%20Untitled%20%28%20Jedi%20Revolver%20Crew%20%29%20-%20TCE%20MIC%20CHECK.mp3?dl=0";

}
