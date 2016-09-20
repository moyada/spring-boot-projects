package com.xyk.util;

public abstract class RepositoryUtil {

    /*public static PageRequest createPageable(ExtDirectStoreReadRequest request) {

        List<Order> orders = new ArrayList<Order>();
        for (SortInfo sortInfo : request.getSorters()) {

            if (sortInfo.getDirection() == SortDirection.ASCENDING) {
                orders.add(new Order(Direction.ASC, sortInfo.getProperty()));
            } else {
                orders.add(new Order(Direction.DESC, sortInfo.getProperty()));
            }
        }

        // Ext JS pages starts with 1, Spring Data starts with 0

        int page = Math.max(request.getPage() - 1, 0);

        if (orders.isEmpty()) {
            return new PageRequest(page, request.getLimit());
        }

        Sort sort = new Sort(orders);
        return new PageRequest(page, request.getLimit(), sort);

    }

    public static DBPageRequest createDBPageable(ExtDirectStoreReadRequest request) {

        List<Order> orders = new ArrayList<Order>();
        for (SortInfo sortInfo : request.getSorters()) {

            if (sortInfo.getDirection() == SortDirection.ASCENDING) {
                orders.add(new Order(Direction.ASC, sortInfo.getProperty()));
            } else {
                orders.add(new Order(Direction.DESC, sortInfo.getProperty()));
            }
        }

        // Ext JS pages starts with 1, Spring Data starts with 0

        int page = request.getPage() != null ? request.getPage() : 0;
            page = Math.max(page - 1, 0);
        int limit = request.getLimit() != null ? request.getLimit() : 25;

        if (orders.isEmpty()) {
            return new DBPageRequest(request.getParams(), page, limit);
        }

        Sort sort = new Sort(orders);
        return new DBPageRequest(request.getParams(),page, limit, sort);

    }*/
}