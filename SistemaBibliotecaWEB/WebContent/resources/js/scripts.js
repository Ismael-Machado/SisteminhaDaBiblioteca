function verificar(xhr, status, args, dlg1, dlg2) {
	if(args.validationFailed) {
		PF(dlg).jq.effect("shake", {times:5}, 100);
	}
	else {
		PF(dlg2).show();
		PF(dlg1).hide();
	}
}