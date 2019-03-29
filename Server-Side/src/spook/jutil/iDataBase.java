/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spook.jutil;

import java.util.List;

public interface iDataBase<T> {
    
    public boolean kaydet(T t) throws Exception;
    
    public boolean guncelle(T t) throws Exception;
    
    public boolean sil(T t) throws Exception;
    
    public List<T> listele(T t) throws Exception;
    
    public T bul(int id, T t) throws Exception;
    
    public List<T> ara(String kolonAdi, String aranan, T t)throws Exception;
    
    public List<T> ara(T t) throws Exception;
    
}
