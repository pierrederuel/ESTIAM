"use strict";

/**
 * Event
 * @description :: Model for storing Event records
 */

module.exports = {
  schema: true,

  attributes: {
    title: 'string',
	image: 'string',
    description: 'string',
	startDate: 'datetime',
	endDate: 'datetime',	
    city: {
      model: 'city'
    },
    toJSON() {
      return this.toObject();
    }
  },

  beforeUpdate: (values, next) => next(),
  beforeCreate: (values, next) => next()
};
