
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ClientWindow extends JFrame{

    private JButton addButton = new JButton("Добавить заявку");
    private JButton lookButton = new JButton("Посмотреть статус заявок");
    private JLabel welcome = new JLabel("Добро пожаловать, " + TradeSystem.currentUser.name);
    private GridBagLayout gbl = new GridBagLayout();
    private GridBagConstraints lookCon = new GridBagConstraints();
    private GridBagConstraints addCon = new GridBagConstraints();
    private GridBagConstraints welCon = new GridBagConstraints();

    ClientWindow () {
        this.setTitle("Пользователь");
        this.setSize(300, 200);
        this.setResizable(false);
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(), fSize = getSize ();
        if (fSize.height > sSize.height) {fSize.height = sSize.height;}
        if (fSize.width  > sSize.width)  {fSize.width = sSize.width;}
        setLocation ((sSize.width - fSize.width)/2, (sSize.height - fSize.height)/3);

        addWindowListener(new AdminWindow.CloseEventListener());

        Container container = this.getContentPane();
        container.setLayout(gbl);

        welCon.gridx = 0;
        welCon.gridy = 1;
        welCon.gridwidth = GridBagConstraints.REMAINDER;
        welCon.fill = GridBagConstraints.BOTH;
        welCon.insets = new Insets(0, 0, 10, 0);
        lookCon.gridx = 0;
        lookCon.gridy = GridBagConstraints.RELATIVE;
        lookCon.fill = GridBagConstraints.BOTH;
        lookCon.insets = new Insets(10, 0, 10, 0);
        addCon.gridx = 0;
        addCon.gridy = GridBagConstraints.RELATIVE;
        addCon.fill = GridBagConstraints.BOTH;
        addCon.insets = new Insets(10, 0, 0, 0);

        welcome.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        welcome.setHorizontalAlignment(JTextField.LEFT);
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        addButton.setHorizontalAlignment(JTextField.CENTER);
        lookButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lookButton.setHorizontalAlignment(JTextField.CENTER);

        gbl.setConstraints(lookButton, lookCon);
        gbl.setConstraints(addButton, addCon);
        gbl.setConstraints(welcome, welCon);

        lookButton.addActionListener(new LookRequestButtonListener());
        addButton.addActionListener(e -> new AddRequestWindow());

        container.add(welcome);
        container.add(lookButton);
        container.add(addButton);

        this.setVisible(true);
    }

    private static class AddRequestWindow extends JFrame {

        static String[] texts = {"Продажа", "Покупка"};
        private GridBagLayout gbl = new GridBagLayout();
        static JTextField productInput = new JTextField("Название продукта");
        static JTextField priceInput = new JTextField("Цена за штуку");
        static JTextField quantityInput = new JTextField("Количество");
        private JButton addButton = new JButton("Разместить заявку");
        static JComboBox<String> box = new JComboBox<>(texts);
        private GridBagConstraints input = new GridBagConstraints();
        private GridBagConstraints button = new GridBagConstraints();

        AddRequestWindow () {

            this.setTitle("Добавить заявку");
            this.setSize(400, 300);
            this.setResizable(false);
            Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(), fSize = getSize ();
            if (fSize.height > sSize.height) {fSize.height = sSize.height;}
            if (fSize.width  > sSize.width)  {fSize.width = sSize.width;}
            setLocation ((sSize.width - fSize.width)/2, (sSize.height - fSize.height)/3);
            addWindowListener(new RequestWindowListener());

            Container container = this.getContentPane();
            container.setLayout(gbl);

            input.gridx = 0;
            input.gridy = GridBagConstraints.RELATIVE;
            input.fill = GridBagConstraints.BOTH;
            input.insets = new Insets(5,10,5,10);

            button.gridx = 0;
            button.gridy = GridBagConstraints.RELATIVE;
            button.insets = new Insets(10,0,10,0);

            productInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            productInput.setHorizontalAlignment(JTextField.LEFT);
            quantityInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            quantityInput.setHorizontalAlignment(JTextField.RIGHT);
            priceInput.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            priceInput.setHorizontalAlignment(JTextField.RIGHT);
            addButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            addButton.setHorizontalAlignment(JTextField.CENTER);
            box.setFont(new Font("Times New Roman", Font.PLAIN, 14));//

            gbl.setConstraints(productInput, input);
            gbl.setConstraints(priceInput, input);
            gbl.setConstraints(quantityInput, input);
            gbl.setConstraints(addButton, button);
            gbl.setConstraints(box, button);

            addButton.addActionListener(new AddRequestListener());

            container.add(productInput);
            container.add(priceInput);
            container.add(quantityInput);
            container.add(box);
            container.add(addButton);

            this.setVisible(true);
        }
    }

    private static class LookRequestButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String msg;
            if (TradeSystem.currentUser.getMsg().equals(""))
                msg = "У вас пока нет удовлетворенных заявок";
            else
                msg = TradeSystem.currentUser.getMsg();
            JOptionPane.showMessageDialog(null, msg, "", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private static class AddRequestListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int type = 0;
            if(AddRequestWindow.box.getSelectedIndex() == 1)
                type = 1;
            int price = Integer.parseInt(AddRequestWindow.priceInput.getText());
            int quantity = Integer.parseInt(AddRequestWindow.quantityInput.getText());
            TradeSystem.addRequest((Client) TradeSystem.currentUser, AddRequestWindow.productInput.getText(), price, quantity, type);
        }
    }

    private static class RequestWindowListener implements WindowListener {

        public void windowOpened(WindowEvent e) {}

        public void windowClosing(WindowEvent e) {
            AddRequestWindow.productInput.setText("Название продукта");
            AddRequestWindow.priceInput.setText("Цена за штуку");
            AddRequestWindow.quantityInput.setText("Количество");
        }

        public void windowClosed(WindowEvent e) {}

        public void windowIconified(WindowEvent e) {}

        public void windowDeiconified(WindowEvent e) {}

        public void windowActivated(WindowEvent e) {}

        public void windowDeactivated(WindowEvent e) {}
    }
}