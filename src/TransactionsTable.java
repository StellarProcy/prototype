import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
 
public class TransactionsTable extends JFrame {
 
    static int i = 0;
 
    public TransactionsTable() {
 
        super("TransactionsTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        ArrayList<MyBean> beans = new ArrayList<MyBean>();
 
        for (int i = 0; i < 30; i++) {
            beans.add(new MyBean("Номер транзакции " + i, "Категория " + i, "Описание " + i));
        }
 
        TableModel model = new MyTableModel(beans);
        JTable table = new JTable(model);
 
        getContentPane().add(new JScrollPane(table));
 
        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new TransactionsTable();
            }
        });
    }
 
    public class MyTableModel implements TableModel {
 
        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
 
        private List<MyBean> beans;
 
        public MyTableModel(List<MyBean> beans) {
            this.beans = beans;
        }
 
        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }
 
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }
 
        public int getColumnCount() {
            return 3;
        }
 
        public String getColumnid_transaction(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return "Номер транзакции";
            case 1:
                return "Категория";
            case 2:
                return "Описание";
            }
            return "";
        }
 
        public int getRowCount() {
            return beans.size();
        }
 
        public Object getValueAt(int rowIndex, int columnIndex) {
            MyBean bean = beans.get(rowIndex);
            switch (columnIndex) {
            case 0:
                return bean.getid_transaction();
            case 1:
                return bean.getcategory_transaction();
            case 2:
                return bean.getDescription();
            }
            return "";
        }
 
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
 
        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }
 
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
 
        }

		@Override
		public String getColumnName(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
 
    }
 
    public class MyBean {
 
        private String id_transaction;
        private String category_transaction;
        private String description;
 
        public MyBean(String id_transaction, String category_transaction, String description) {
            this.setid_transaction(id_transaction);
            this.setcategory_transaction(category_transaction);
            this.setDescription(description);
        }
 
        public void setid_transaction(String id_transaction) {
            this.id_transaction = id_transaction;
        }
 
        public String getid_transaction() {
            return id_transaction;
        }
 
        public void setcategory_transaction(String category_transaction) {
            this.category_transaction = category_transaction;
        }
 
        public String getcategory_transaction() {
            return category_transaction;
        }
 
        public void setDescription(String description) {
            this.description = description;
        }
 
        public String getDescription() {
            return description;
        }
    }
}
