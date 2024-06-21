/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.table.DefaultTableModel;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
            String sql = "insert into produtos (nome, valor, status) values (?, ?, ?);";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setString(3, produto.getStatus());
            try {
                BigDecimal valor = new BigDecimal(produto.getValor());
                prep.setBigDecimal(2, valor);
                    int adicionado = prep.executeUpdate();
                    if (adicionado > 0) {
                        JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso!");
                    }
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "O valor deve ser um número decimal válido!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        String sql = "SELECT id, nome, valor, status FROM produtos;";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            //DefaultTableModel model = new DefaultTableModel();
            //model.addColumn("Nome Cliente");
            //model.addColumn("Telefone");
            //model.addColumn("Nome Serviço");
            //model.addColumn("Valor Serviço");
            //model.addColumn("Acrescimos Diversos");
            //model.addColumn("Desconto");
            //model.addColumn("Valor Total");

            
            while (resultset.next()) {
                ProdutosDTO model = new ProdutosDTO();
                model.setId(resultset.getInt("id"));
                model.setNome(resultset.getString("nome"));
                model.setValor(resultset.getInt("valor"));
                model.setStatus(resultset.getString("status"));
                
                listagem.add(model);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listagem;
    }
    
    
    
        
}

