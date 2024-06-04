/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cajero.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author panch
 */
public class Cajero extends javax.swing.JFrame {

    private final String[] cafeArr = {"Americano", "Express", "Irlandes"};
    private final String[] teArr = {"Verde", "Negro", "Manzana"};
    private final String[] chocolateArr = {"A la mexicana", "A la española", "A la francesa"};
    private final String[] mateArr = {"Dulce", "De leche", "Amargo"};
    private int intentosRestantes = 3;

    private HashMap<String, Integer> preciosCafe = new HashMap<>();
    private HashMap<String, Integer> preciosTe = new HashMap<>();
    private HashMap<String, Integer> preciosChocolate = new HashMap<>();
    private HashMap<String, Integer> preciosMate = new HashMap<>();

    /**
     * Creates new form Cajero
     */
    public Cajero() {
        initComponents();
        //  Se deshabilitan componentes
        disableComponents();
        //  Se configura el valor de cada subtipo de producto
        initPrecios();
        comboBoxCatalogo();
        //  Se agregan valores a la tabla
        addToTable();
        //  Se agregan las opciones de subproducto al combobox
        setModelsToComboBox();
        //  Se setean los valores del spinner a positivos
        setSpinnerModelToposisitve();
        
        
        setImageLabel(imgCoffe, "src/assets/taza-de-cafe.png");
        setImageLabel(imgTe, "src/assets/te.png");
        setImageLabel(imgChocolate, "src/assets/leche-con-chocolate.png");
        setImageLabel(imgMate, "src/assets/te-de-mate.png");
    }
    
    private void setImageLabel(JLabel label, String root) {
        ImageIcon image = new ImageIcon(root);
        
        Icon icon = new ImageIcon(image.getImage()
                .getScaledInstance(label.getWidth(), 
                        label.getHeight(), 
                        java.awt.Image.SCALE_DEFAULT));
        label.setIcon(icon);
        this.repaint();
    }

    private void addToTable() {
        //  
        btnAgregarCafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCafeActionPerformed(evt);
            }
        });
        btnAgregarTe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTeActionPerformed(evt);
            }
        });
        btnAgregarChocolate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarChocolateActionPerformed(evt);
            }
        });
        btnAgregarMate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMateActionPerformed(evt);
            }
        });
    }

    private void comboBoxCatalogo() {
        comboBoxCafe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio(comboBoxCafe, preciosCafe, textPaneCafe);
            }
        });
        comboBoxTe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio(comboBoxTe, preciosTe, textPaneTe);
            }
        });
        comboBoxChocolate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio(comboBoxChocolate, preciosChocolate, textPaneChocolate);
            }
        });
        comboBoxMate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio(comboBoxMate, preciosMate, textPaneMate);
            }
        });
    }

    private void disableComponents() {
        //  Se deshabilitan componentes
        comboBoxCafe.setEnabled(false);
        comboBoxChocolate.setEnabled(false);
        comboBoxMate.setEnabled(false);
        comboBoxTe.setEnabled(false);
        btnAgregarCafe.setEnabled(false);
        btnAgregarChocolate.setEnabled(false);
        btnAgregarMate.setEnabled(false);
        btnAgregarTe.setEnabled(false);
        spinnerCafe.setEnabled(false);
        spinnerChocolate.setEnabled(false);
        spinnerMate.setEnabled(false);
        spinnerTe.setEnabled(false);
        btnCobrar.setEnabled(false);

        //  Se deshabilita la opcion de edicion
        textPaneCafe.setEditable(false);
        textPaneChocolate.setEditable(false);
        textPaneMate.setEditable(false);
        textPaneTe.setEditable(false);
    }

    private void agregarFila(String tipo, String subTipo, int cantidad, int precio) {
        int subtotal = cantidad * precio;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{tipo + " " + subTipo, cantidad, subtotal});
    }

// Modificar los ActionListener de los botones "Agregar"
    private void btnAgregarCafeActionPerformed(java.awt.event.ActionEvent evt) {
        String subTipo = (String) comboBoxCafe.getSelectedItem();
        int cantidad = (int) spinnerCafe.getValue();
        int precio = preciosCafe.get(subTipo);
        agregarFila("Café", subTipo, cantidad, precio);
    }

    private void btnAgregarTeActionPerformed(java.awt.event.ActionEvent evt) {
        String subTipo = (String) comboBoxTe.getSelectedItem();
        int cantidad = (int) spinnerTe.getValue();
        int precio = preciosTe.get(subTipo);
        agregarFila("Té", subTipo, cantidad, precio);
    }

    private void btnAgregarChocolateActionPerformed(java.awt.event.ActionEvent evt) {
        String subTipo = (String) comboBoxChocolate.getSelectedItem();
        int cantidad = (int) spinnerChocolate.getValue();
        int precio = preciosChocolate.get(subTipo);
        agregarFila("Chocolate", subTipo, cantidad, precio);
    }

    private void btnAgregarMateActionPerformed(java.awt.event.ActionEvent evt) {
        String subTipo = (String) comboBoxMate.getSelectedItem();
        int cantidad = (int) spinnerMate.getValue();
        int precio = preciosMate.get(subTipo);
        agregarFila("Mate", subTipo, cantidad, precio);
    }

    private void initPrecios() {
        preciosCafe.put("Americano", 20);
        preciosCafe.put("Express", 25);
        preciosCafe.put("Irlandes", 30);

        preciosTe.put("Verde", 15);
        preciosTe.put("Negro", 20);
        preciosTe.put("Manzana", 18);

        preciosChocolate.put("A la mexicana", 25);
        preciosChocolate.put("A la española", 30);
        preciosChocolate.put("A la francesa", 35);

        preciosMate.put("Dulce", 10);
        preciosMate.put("De leche", 15);
        preciosMate.put("Amargo", 12);
    }

    private void actualizarPrecio(JComboBox<String> comboBox, HashMap<String, Integer> precios, JTextPane textPane) {
        String selectedItem = (String) comboBox.getSelectedItem();
        if (selectedItem != null) {
            int precio = precios.getOrDefault(selectedItem, 0);
            textPane.setText("$" + precio);
        }
    }

    private void setModelsToComboBox() {
        // Set combo box models
        comboBoxCafe.setModel(new DefaultComboBoxModel<>(cafeArr));
        comboBoxTe.setModel(new DefaultComboBoxModel<>(teArr));
        comboBoxChocolate.setModel(new DefaultComboBoxModel<>(chocolateArr));
        comboBoxMate.setModel(new DefaultComboBoxModel<>(mateArr));
    }

    private void setSpinnerModelToposisitve() {
        // Set spinner models to prevent negative values
        spinnerCafe.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spinnerTe.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spinnerChocolate.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        spinnerMate.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imgCoffe1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneCafe = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneTe = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        textPaneChocolate = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        textPaneMate = new javax.swing.JTextPane();
        spinnerCafe = new javax.swing.JSpinner();
        comboBoxCafe = new javax.swing.JComboBox<>();
        comboBoxTe = new javax.swing.JComboBox<>();
        comboBoxChocolate = new javax.swing.JComboBox<>();
        comboBoxMate = new javax.swing.JComboBox<>();
        spinnerTe = new javax.swing.JSpinner();
        spinnerChocolate = new javax.swing.JSpinner();
        spinnerMate = new javax.swing.JSpinner();
        btnAgregarCafe = new javax.swing.JButton();
        btnAgregarTe = new javax.swing.JButton();
        btnAgregarChocolate = new javax.swing.JButton();
        btnAgregarMate = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnCobrar = new javax.swing.JButton();
        txtContrasena = new javax.swing.JTextField();
        imgTe = new javax.swing.JLabel();
        imgCoffe = new javax.swing.JLabel();
        imgMate = new javax.swing.JLabel();
        imgChocolate = new javax.swing.JLabel();

        imgCoffe1.setBackground(new java.awt.Color(204, 0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cajero");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(556, 720));

        jPanel1.setPreferredSize(new java.awt.Dimension(556, 720));

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        txtUsuario.setMinimumSize(new java.awt.Dimension(70, 22));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jLabel3.setText("Café");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(30, 16));

        jLabel4.setText("Té");

        jLabel5.setText("Chocolate");

        jLabel6.setText("Mate");

        textPaneCafe.setEditable(false);
        jScrollPane1.setViewportView(textPaneCafe);

        textPaneTe.setEditable(false);
        jScrollPane2.setViewportView(textPaneTe);

        textPaneChocolate.setEditable(false);
        jScrollPane3.setViewportView(textPaneChocolate);

        textPaneMate.setEditable(false);
        jScrollPane4.setViewportView(textPaneMate);

        comboBoxCafe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxCafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxCafeActionPerformed(evt);
            }
        });

        comboBoxTe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxChocolate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboBoxMate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarCafe.setText("Agregar");

        btnAgregarTe.setText("Agregar");

        btnAgregarChocolate.setText("Agregar");

        btnAgregarMate.setText("Agregar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Artículo", "Cantidad", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);

        btnCobrar.setText("Cobrar");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        txtContrasena.setMinimumSize(new java.awt.Dimension(70, 22));

        imgTe.setBackground(new java.awt.Color(204, 0, 0));

        imgCoffe.setBackground(new java.awt.Color(204, 0, 0));

        imgMate.setBackground(new java.awt.Color(204, 0, 0));

        imgChocolate.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnIngresar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtContrasena, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(332, 332, 332))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerCafe, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgCoffe, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAgregarTe)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(spinnerTe, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarChocolate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarMate))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxTe, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(imgTe, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxChocolate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(spinnerChocolate, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(imgChocolate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(67, 67, 67)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(imgMate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(comboBoxMate, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(spinnerMate, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarCafe)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(comboBoxCafe, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1))
                                    .addGap(431, 431, 431))))
                        .addGap(0, 34, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIngresar)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imgTe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgCoffe, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(imgMate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imgChocolate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxCafe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxTe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxChocolate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerCafe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerTe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerChocolate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerMate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarCafe)
                    .addComponent(btnAgregarTe)
                    .addComponent(btnAgregarChocolate)
                    .addComponent(btnAgregarMate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        // TODO add your handling code here:
        String usuario;
        String contrasena;

        usuario = txtUsuario.getText().toString();
        contrasena = txtContrasena.getText().toString();

        if (!usuario.equals("admin")) {
            intentosRestantes--;
            JOptionPane.showMessageDialog(this,
                        "Error: El usuario no es correcto. Intentos restantes: " + intentosRestantes,
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        } else if (!contrasena.equals("1234")) {
            intentosRestantes--;
            JOptionPane.showMessageDialog(this,
                        "Error: La contraseña no es correcta. Intentos restantes: " + intentosRestantes,
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        } else if (!usuario.equals("admin") && !contrasena.equals("1234")) {
            intentosRestantes--;
            JOptionPane.showMessageDialog(this,
                        "Error: Las credenciales no son correctas. Intentos restantes: " + intentosRestantes,
                        "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
        
        if (intentosRestantes > 0 && usuario.equals("admin") && contrasena.equals("1234")) {
            System.out.println("Las credenciales son correctas");
            habilitarComponentes();
            limpiarCampos();
        }

        if (intentosRestantes <= 0) {
            bloquearSistema();
        } 
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void bloquearSistema() {
        JOptionPane.showMessageDialog(this,
                    "El sistema ha sido bloqueado.",
                    "Sistema Bloqueado", JOptionPane.ERROR_MESSAGE);
    }

    private void habilitarComponentes() {
        comboBoxCafe.setEnabled(true);
        comboBoxChocolate.setEnabled(true);
        comboBoxMate.setEnabled(true);
        comboBoxTe.setEnabled(true);
        btnAgregarCafe.setEnabled(true);
        btnAgregarChocolate.setEnabled(true);
        btnAgregarMate.setEnabled(true);
        btnAgregarTe.setEnabled(true);
        spinnerCafe.setEnabled(true);
        spinnerChocolate.setEnabled(true);
        spinnerMate.setEnabled(true);
        spinnerTe.setEnabled(true);
        btnCobrar.setEnabled(true);
    }

    private void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
    }

    private void comboBoxCafeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxCafeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxCafeActionPerformed

    /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cajero().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCafe;
    private javax.swing.JButton btnAgregarChocolate;
    private javax.swing.JButton btnAgregarMate;
    private javax.swing.JButton btnAgregarTe;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JComboBox<String> comboBoxCafe;
    private javax.swing.JComboBox<String> comboBoxChocolate;
    private javax.swing.JComboBox<String> comboBoxMate;
    private javax.swing.JComboBox<String> comboBoxTe;
    private javax.swing.JLabel imgChocolate;
    private javax.swing.JLabel imgCoffe;
    private javax.swing.JLabel imgCoffe1;
    private javax.swing.JLabel imgMate;
    private javax.swing.JLabel imgTe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JSpinner spinnerCafe;
    private javax.swing.JSpinner spinnerChocolate;
    private javax.swing.JSpinner spinnerMate;
    private javax.swing.JSpinner spinnerTe;
    private javax.swing.JTextPane textPaneCafe;
    private javax.swing.JTextPane textPaneChocolate;
    private javax.swing.JTextPane textPaneMate;
    private javax.swing.JTextPane textPaneTe;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
