import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

	public class Temp {
	public static void main(String[] args) throws IOException {
		String src = "iVBORw0KGgoAAAANSUhEUgAAAAwAAAAMCAYAAABWdVznAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTNXG14zYAAAAnSURBVCiRY/z//z8DseDNmzf/mYhWDQWjGoaoBpY3b94QnzYYGBgAmewKnSmh86IAAAAASUVORK5CYII=";
		byte[] fileByte = Base64.decodeBase64(src);
		FileUtils.writeByteArrayToFile(new File("D://","test.png"), fileByte); 
	}
}
