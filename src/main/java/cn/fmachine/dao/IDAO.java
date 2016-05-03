package cn.fmachine.dao;

/**
 * Author: Xin Ming
 * Email: m.xinerd@gmail.com
 * Date: 4/30/16
 */
public interface IDAO<T1, T2, K> {

    public boolean doCreate(T1 vo) throws Exception;

    public T2 doFind() throws Exception;

    public T2 doFind(int pageNumber, int pageSize) throws Exception;

    public boolean doDelete(K id) throws Exception;

    public int getCount() throws Exception;

}
