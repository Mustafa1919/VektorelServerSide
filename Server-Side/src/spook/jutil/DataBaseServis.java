/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spook.jutil;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import spook.hbUtil.hibernateUtil;

public class DataBaseServis<T> implements iDataBase<T>{
    private Session session;
    Transaction tt;
    private void baglantiAc(){
        session = (Session) hibernateUtil.getSessionFactory().openSession();
        tt = session.beginTransaction();
    }
    
    private void baglantiKapat(){
        tt.commit();
        session.close();
    }
    @Override
    public boolean kaydet(T t) throws Exception{
        baglantiAc();
        session.save(t);
        baglantiKapat();
        return true;
    }

    @Override
    public boolean guncelle(T t) throws Exception{
        baglantiAc();
        session.update(t);
        baglantiKapat();
        return true;
    }

    @Override
    public boolean sil(T t) throws Exception{
        baglantiAc();
        session.delete(t);
        baglantiKapat();
        return true;
    }

    @Override
    public List<T> listele(T t) throws Exception{
        baglantiAc();
        Criteria cc = session.createCriteria(t.getClass());
		List<T> liste = cc.list();
        baglantiKapat();
        return liste;
    }

	@Override
    public T bul(int id, T t) throws Exception{
        baglantiAc();
        Criteria cc = session.createCriteria(t.getClass());
        cc.add(Restrictions.eq("id", id));
        T temo = (T) cc.uniqueResult();
        baglantiKapat();
        return temo;
    }

    @Override
    public List<T> ara(String kolonAdi, String aranan, T t) throws Exception{
        baglantiAc();
        Criteria cc = session.createCriteria(t.getClass());
        cc.add(Restrictions.ilike(kolonAdi, "'%"+aranan+"%'"));
        List<T> liste = cc.list();
        baglantiKapat();
        return liste;
    }

    @Override
    public List<T> ara(T t) throws Exception{
        List<T> liste = null;
        
        Class cl = t.getClass();
        Field[] fl = cl.getDeclaredFields();
        
        baglantiAc();;
        Criteria cc = session.createCriteria(t.getClass());
        for (Field f : fl){
            f.setAccessible(true);
            if(f.get(t) != null && f.get(t).toString().equals("0")){
                cc.add(Restrictions.ilike(f.getName(), "%"+f.get(t)+"%"));
            }
        }
        liste = cc.list();
        return liste;
    }
    
}
