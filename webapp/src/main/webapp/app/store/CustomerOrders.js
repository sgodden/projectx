Ext.define('AM.store.CustomerOrders', {
    extend: 'Ext.data.Store',
    model: 'AM.model.CustomerOrder',
    autoLoad: false,
    storeId: 'customerOrders',
    proxy: AM.AppCtx.getProxy('customerOrders')
});