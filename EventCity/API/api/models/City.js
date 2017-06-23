"use strict";

/**
 * City
 * @description :: Model for storing City records
 */

module.exports = {
  schema: true,

  attributes: {
    name: 'string',
	image: 'string',
    events: {
      collection: 'event',
      via: 'city'
    },
    toJSON() {
      return this.toObject();
    }
  },

  beforeUpdate: (values, next) => next(),
  beforeCreate: (values, next) => next()
};
