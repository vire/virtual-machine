import Ember from 'ember';
import layout from '../templates/components/node-details';

export default Ember.Component.extend({
  layout: layout,
  cachedNodes: {},
  lasteRequestedID: null,
  fetchOrRetrieve: function() {
    const nodeID = this.get('node._metadata.id');
    const cn = this.get('cachedNodes');
    let self = this;
    if(!cn[nodeID] && nodeID !== this.get('lasteRequestedID')) {
      this.set('lasteRequestedID', nodeID);
      this.fetchNode(nodeID).then(function(response) {
        response._embedded.map(n => {
          n.created_at = moment(n.created_at).format('MMMM Do YYYY, HH:mm:ss');
          return n;
        });
        cn[nodeID] = response._embedded;
      }, function(reason) {
        console.error(reason);
      });
    }

    this.set('nodeDetails', cn[nodeID]);
  }.observes('node'),

  fetchNode(nodeID) {
    return new Ember.RSVP.Promise(function(resolve, reject) {
      Ember.$.ajax({
        url: 'http://localhost:8080/vm-backend/read/nodes/' + nodeID + '/systems',
        dataType: 'json',
        success: resolve,
        error: function(jqXHR) {
          reject(jqXHR);
        }
      });
    });
  },

  displayLayer: function () {
    var layers = this.get('layers');
    layers.map(l => {
      l.health = (parseInt(l.current_robustness) / parseInt(l.max_robustness) * 100).toFixed(2) + '%';
      return l;
    });
  }.observes('layers')
});
