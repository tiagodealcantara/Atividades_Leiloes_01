package DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import DAO.config.conectaDAO;
import DTO.ProdutosDTO;
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
    
      public void venderProduto(int id){
            String sql = "update produtos set status = 'Vendido' where id = ?;";
        try {
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
        
    }  
    
        
}

