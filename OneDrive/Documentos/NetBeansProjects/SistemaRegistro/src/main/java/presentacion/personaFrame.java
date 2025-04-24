/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import modelo.Persona;
import negocio.PersonaServicio;

/**
 *
 * @author sebas
 */
public class personaFrame extends javax.swing.JFrame {

    /**
     * Creates new form personaFrame
     */
    private PersonaServicio servicio;
    private SimpleDateFormat formato;
    // Crear borde rojo y negro para los elementos del formulario
    private final Border bordeRojo = BorderFactory.createLineBorder(Color.RED, 2);
    private final Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK, 1);

    public personaFrame() {
        initComponents();
        servicio = new PersonaServicio();
        formato = new SimpleDateFormat("dd/MM/yyyy");
        cargarDatos();
    }

    public void cargarDatos() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Correo");
        model.addColumn("Cedula");
        model.addColumn("Fecha Nacimiento");
        for (Persona p : servicio.ListarPersonas()) {
            Object[] fila = {p.getId(), p.getNombre(), p.getApellido(), p.getCorreo(), p.getCedula(), p.getFechaNacimiento()};
            model.addRow(fila);

        }
        tblPersona.setModel(model);
    }

    public void agregar() {
        // Si el formulario esta lleno
        if (ValidarFormulario()) {
            System.out.println("Intentando registrar");

            int edad = PersonaServicio.CalcularEdad(DtFechaNacimiento.getDate());
            Persona nuevaPersona = new Persona(nomTxtField.getText(), apellidoTxtField.getText(),
                    correoTxtField.getText(), cedTxtField.getText(), DtFechaNacimiento.getDate(), edad);

            servicio.AgregarNuevaPersona(nuevaPersona);
            cargarDatos();

        } else {
            System.out.println("Falta llenar un input");
            // Mostrar alerta de que llene el formulario

        }

    }

    public void editar() {
        int fila = tblPersona.getSelectedRow();
        if (fila >= 0) {

            int id = (int) tblPersona.getValueAt(fila, 0);

            String nuevoNombre = nomTxtField.getText();
            String nuevoApellido = apellidoTxtField.getText();
            String nuevoCorreo = correoTxtField.getText();
            String nuevaCedula = cedTxtField.getText();
            Date nuevaFecha = DtFechaNacimiento.getDate();
            int nuevaEdad = PersonaServicio.CalcularEdad(nuevaFecha);

            Persona personaEditada = new Persona(nuevoNombre, nuevoApellido, nuevoCorreo, nuevaCedula, nuevaFecha, nuevaEdad);
            personaEditada.setId(id);

            servicio.ActualizarPersona(personaEditada);
            cargarDatos();
        } else {
            System.out.println("Seleccione una fila para editar.");
        }
    }

    public void eliminar() {
        int fila = tblPersona.getSelectedRow();
        if (fila >= 0) {
            int id = (int) tblPersona.getValueAt(fila, 0);

            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar esta persona?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                servicio.EliminarPersonaPorId(id);
                cargarDatos();
                vaciarCampos();

            }
        } else {
            System.out.println("Seleccione una fila para eliminar.");
        }
    }

    private void vaciarCampos() {

        nomTxtField.setText("");
        apellidoTxtField.setText("");
        correoTxtField.setText("");
        cedTxtField.setText("");
        DtFechaNacimiento.setDate(null);
    }

    private void rellenarcampos() {
        int fila = tblPersona.getSelectedRow();
        if (fila
                >= 0) {
            // Asumiendo que tu DefaultTableModel tiene estas columnas en este orden:
            // 0: ID, 1: Nombre, 2: Apellido, 3: Correo, 4: Cedula, 5: FechaNacimiento
            String nombre = tblPersona.getValueAt(fila, 1).toString();
            String apellido = tblPersona.getValueAt(fila, 2).toString();
            String correo = tblPersona.getValueAt(fila, 3).toString();
            String cedula = tblPersona.getValueAt(fila, 4).toString();
            Date fechaNac = (Date) tblPersona.getValueAt(fila, 5);

            // Rellenar los campos del formulario:
            nomTxtField.setText(nombre);
            apellidoTxtField.setText(apellido);
            correoTxtField.setText(correo);
            cedTxtField.setText(cedula);
            DtFechaNacimiento.setDate(fechaNac);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        apellidoLabel = new javax.swing.JLabel();
        nombreLabel = new javax.swing.JLabel();
        nomTxtField = new javax.swing.JTextField();
        correoLabel = new javax.swing.JLabel();
        fechaNLabel = new javax.swing.JLabel();
        ceduLabel = new javax.swing.JLabel();
        correoTxtField = new javax.swing.JTextField();
        apellidoTxtField = new javax.swing.JTextField();
        cedTxtField = new javax.swing.JTextField();
        btnRegistrarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPersona = new javax.swing.JTable();
        DtFechaNacimiento = new org.jdesktop.swingx.JXDatePicker();
        btnActualizarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnVaciarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        apellidoLabel.setText("Apellido:");

        nombreLabel.setText("Nombre: ");

        nomTxtField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nomTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomTxtFieldActionPerformed(evt);
            }
        });

        correoLabel.setText("Correo:");

        fechaNLabel.setText("Fecha Nacimiento:");

        ceduLabel.setText("Cédula:");

        correoTxtField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        correoTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoTxtFieldActionPerformed(evt);
            }
        });

        apellidoTxtField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        apellidoTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoTxtFieldActionPerformed(evt);
            }
        });

        cedTxtField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cedTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedTxtFieldActionPerformed(evt);
            }
        });

        btnRegistrarUsuario.setText("Guardar");
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });

        tblPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPersonaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPersona);

        btnActualizarUsuario.setText("Editar");
        btnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        btnVaciarUsuario.setText("⟳");
        btnVaciarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(nombreLabel)
                            .addGap(142, 142, 142)
                            .addComponent(nomTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(correoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(apellidoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaNLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ceduLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(correoTxtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(apellidoTxtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(cedTxtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addComponent(DtFechaNacimiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminarUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarUsuario)))
                .addGap(26, 26, 26)
                .addComponent(btnVaciarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nomTxtField))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoLabel)
                    .addComponent(apellidoTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correoLabel)
                    .addComponent(correoTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ceduLabel)
                    .addComponent(cedTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaNLabel)
                    .addComponent(DtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarUsuario)
                    .addComponent(btnActualizarUsuario)
                    .addComponent(btnEliminarUsuario)
                    .addComponent(btnVaciarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomTxtFieldActionPerformed


    }//GEN-LAST:event_nomTxtFieldActionPerformed

    private void correoTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoTxtFieldActionPerformed


    }//GEN-LAST:event_correoTxtFieldActionPerformed

    private void apellidoTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoTxtFieldActionPerformed


    }//GEN-LAST:event_apellidoTxtFieldActionPerformed

    private void cedTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedTxtFieldActionPerformed


    }//GEN-LAST:event_cedTxtFieldActionPerformed

    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioActionPerformed

        agregar();
    }//GEN-LAST:event_btnRegistrarUsuarioActionPerformed

    private void btnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsuarioActionPerformed
        editar();
        //btnRegistrarUsuario.invalidate();

    }//GEN-LAST:event_btnActualizarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void tblPersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPersonaMouseClicked
        rellenarcampos();

    }//GEN-LAST:event_tblPersonaMouseClicked

    private void btnVaciarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarUsuarioActionPerformed
        vaciarCampos();
    }//GEN-LAST:event_btnVaciarUsuarioActionPerformed

    private boolean ValidarFormulario() {
        // pintar los bordes utilizando operador ternario
        nomTxtField.setBorder(nomTxtField.getText().isEmpty() ? bordeRojo : bordeNegro);
        apellidoTxtField.setBorder(apellidoTxtField.getText().isEmpty() ? bordeRojo : bordeNegro);
        cedTxtField.setBorder(cedTxtField.getText().isEmpty() ? bordeRojo : bordeNegro);
        correoTxtField.setBorder(correoTxtField.getText().isEmpty() ? bordeRojo : bordeNegro);

        return !(nomTxtField.getText().isEmpty() || apellidoTxtField.getText().isEmpty()
                || cedTxtField.getText().isEmpty() || correoTxtField.getText().isEmpty());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(personaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(personaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(personaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(personaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker DtFechaNacimiento;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JTextField apellidoTxtField;
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnRegistrarUsuario;
    private javax.swing.JButton btnVaciarUsuario;
    private javax.swing.JTextField cedTxtField;
    private javax.swing.JLabel ceduLabel;
    private javax.swing.JLabel correoLabel;
    private javax.swing.JTextField correoTxtField;
    private javax.swing.JLabel fechaNLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomTxtField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTable tblPersona;
    // End of variables declaration//GEN-END:variables
}
