package dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    public abstract void inserir(T entidade);
    public abstract List<T> listar();
}
