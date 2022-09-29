import dateFormat from "dateformat";

const now = new Date();
export default (await import('vue')).defineComponent({
name: 'AuctionNavbar',
data() {
return {
currentDate: dateFormat(now, "dddd mmmm dS, yyyy")
};
},
methods: {
getDate() {
return this.currentDate;
}
}
});
function __VLS_template() {
// @ts-ignore
[getDate];
return {};
}
