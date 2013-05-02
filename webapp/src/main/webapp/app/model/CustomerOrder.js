Ext.define('AM.model.CustomerOrder', {
    extend: 'Ext.data.Model',
    fields: [
        'id',
        {name: 'customerReference'},
        {name: 'orderNumber'},
        {name: 'bookingDate', type: Ext.data.Types.DATE, dateFormat: 'c'}
    ],
    validations: [
        {type: 'presence', field: 'customerReference'},
        {type: 'length', field: 'customerReference', min: 1},
        {type: 'presence', field: 'bookingDate'}
    ],
    proxy: AM.AppCtx.getProxy('customerOrders')
});