/** @type {import('tailwindcss').Config} */
import plugin from 'tailwindcss/plugin';

export default {
  content: ['./index.html', './src/**/*.{vue,js,ts}'],
  theme: {
    extend: {
      colors: {
        text: '#ffffff',
        background: '#141414',
        primary: '#47779e',
        secondary: '#53525a',
        accent: '#5686ae'
      }
    },
    fontSize: {
      sm: '0.750rem',
      base: '1rem',
      xl: '1.333rem',
      '2xl': '1.777rem',
      '3xl': '2.369rem',
      '4xl': '3.158rem',
      '5xl': '4.210rem'
    },
    fontFamily: {
      heading: 'Inter',
      body: 'Inter'
    },
    fontWeight: {
      normal: '400',
      bold: '700'
    }
  },
  plugins: [
    plugin(({ addBase, theme }) => {
      addBase({
        html: {
          color: theme('colors.text'),
          background: theme('colors.background'),
          fontFamily: theme('fontFamily.body')
        },
        h1: {
          fontSize: theme('fontSize.3xl'),
          fontWeight: theme('fontWeight.bold'),
          fontFamily: theme('fontFamily.heading')
        },
        h2: {
          fontSize: theme('fontSize.2xl'),
          fontWeight: theme('fontWeight.bold'),
          fontFamily: theme('fontFamily.heading')
        },
        h3: {
          fontSize: theme('fontSize.xl'),
          fontWeight: theme('fontWeight.bold'),
          fontFamily: theme('fontFamily.heading')
        },
        a: { fontSize: theme('fontSize.xl') },
        button: { fontSize: theme('fontSize.base') }
      });
    })
  ]
};
