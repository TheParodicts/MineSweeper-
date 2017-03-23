import javax.swing.JOptionPane;

public class PUMessage
{
	//Pop up msg box class
    public static void infoBox(MyPanel panel, String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(panel, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}