package classPremiere;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


 
public class Tableau extends JFrame {
   
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane tableau;
	private TableColumnModel modeleColonne;
	private JTableHeader header;
	private Set<Capteur> treeSet;
 
    public Tableau(Set<Capteur> capteur) {
        super("tableau");
        treeSet = new TreeSet<>(capteur);
        List<Capteur> liste = new ArrayList<>();
        for(Capteur c : capteur) {
        	if (c.getConnect())
        		liste.add(c);
        }
        TableModel tableModel = new ModeleTableau(liste);
        table = new JTable(tableModel);
        modeleColonne = table.getColumnModel();
        for(int i=0;i<4;i++) {
        	modeleColonne.getColumn(i).setCellRenderer(new CellRenderer());
        }
        header = table.getTableHeader();
        
        tableau = new JScrollPane(table);
        add(tableau);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    class CellRenderer extends DefaultTableCellRenderer {
    	private final Color rouge = new Color(247,35,12);
    	
    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    		Capteur c = ((ModeleTableau)table.getModel()).getCapteur(row);
    		
    		System.out.println(c.getNom());
    		setText(String.valueOf(value));
    		setHorizontalAlignment(JLabel.LEFT);
    		if (c.valeurExtSeuil()) {
    			this.setBackground(rouge);
    		}
    		else {
    			this.setBackground(Color.WHITE);
    		}
    		return this;
    	}
    }
 
    public static void main(String[] args) {
    	Set<Capteur> liste= new TreeSet<>();
    	Capteur c1 = new Capteur("1","ELECTRICITE:U3:2:devantSalle3");
 		liste.add(c1);
 		Capteur c2=new Capteur("2","EAU:U4:1:devantSalle6");
 		liste.add(c2);
 		Capteur c3 = new Capteur("3","AIR_COMPRIME:U1:1:devantSalle1");
 		liste.add(c3);
 		Capteur c4 = new Capteur("4","TEMPERATURE:U2:1:devantSalle6");
 		liste.add(c4);
 		Capteur c5 = new Capteur("5","TEMPERATURE:U3:3:devantSalle");
 		liste.add(c5);
 		c1.update(5.0f);
 		c2.update(5.0f);
 		c3.update(5.0f);
 		c4.update(20.0f);
 		c5.update(20.0f);
 	    Tableau t = new Tableau(liste);
 	    t.setVisible(true);
    }
}
