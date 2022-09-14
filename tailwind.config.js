/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./public.html",
    "./src/**/*.{html,js,vue}"
  ],
  theme: {
    extend: {},
  },
  plugins: [
    require('flowbite/plugin')
  ],
}
