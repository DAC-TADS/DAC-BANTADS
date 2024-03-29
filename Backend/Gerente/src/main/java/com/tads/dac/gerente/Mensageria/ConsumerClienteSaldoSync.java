/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tads.dac.gerente.Mensageria;
import com.tads.dac.gerente.DTOs.GerenciadoSaldoDTO;
import com.tads.dac.gerente.model.Gerenciados;
import com.tads.dac.gerente.model.Gerente;
import com.tads.dac.gerente.repository.GerenciadosRepository;
import com.tads.dac.gerente.repository.GerenteRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerClienteSaldoSync {
    
    @Autowired
    private GerenciadosRepository rep;
    
    @Autowired GerenteRepository repGer;
   
    @RabbitListener(queues = "gerente")
    public void syncModuloCliente(@Payload GerenciadoSaldoDTO dto){
        
        Optional<Gerenciados> op = rep.findById(dto.getIdConta());
        if(op.isPresent()){
            
            Gerenciados ger = op.get();
            //Se saldo for positivo true se não false
            if(dto.getSaldo().compareTo(BigDecimal.ZERO) > -1){
                ger.setSaldoPositivo(true);
            }else{
                ger.setSaldoPositivo(false);
            }
            
            if(dto.getIdGerente() != null){
                Optional<Gerente> gerente =  repGer.findById(dto.getIdGerente());
                if (gerente.isPresent()) {
                    ger.setGerenteId(gerente.get());
                }
            }
            
            
            rep.save(ger);
        }
        
    }
    
}
