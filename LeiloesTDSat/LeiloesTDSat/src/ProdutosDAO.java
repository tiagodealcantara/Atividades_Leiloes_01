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
        
        return listagem;
    }
    
    
    
        
}

