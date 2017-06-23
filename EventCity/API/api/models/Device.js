"use strict";

/**
 * Device
 * @description :: Model for storing Device records
 */

module.exports = {
  schema: true,

  attributes: {
    uuid: {
      type: 'string',
      required: true
    },
    toJSON() {
      return this.toObject();
    }
  },

  beforeUpdate: (values, next) => next(),
  beforeCreate: (values, next) => next()
};
