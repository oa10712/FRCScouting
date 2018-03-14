'use strict';

const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const dbSchema = new Schema({
  number: String,
  matches: [
    {
      number: Number,
      auto: {
        passedLine: Boolean,
        switch: Boolean,
        scale: Boolean
      },
      tele: {
        switch: Number,
        scale: Number,
        exchange: Number,
        climb: Boolean
      },
      notes: String
    }
  ],

  pit: {
    social: [
      {
        site: String,
        handle: String
      }
    ],
    awards: {
      chairmans: Boolean,
      woodie: Boolean,
      deans: Boolean,
      entrepreneurship: Boolean
    }
  }
});

module.exports = mongoose.model('Team', dbSchema);
