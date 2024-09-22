document.addEventListener('DOMContentLoaded', async () => {
    const connectWalletButton = document.getElementById('connectWalletButton');
    const logoutWalletButton = document.getElementById('logoutWalletButton');
    const walletInfoText = document.getElementById('walletInfo');
    let connectedAccount = null;

    // Kiểm tra Phantom Wallet có được cài đặt hay không
    if (window.solana && window.solana.isPhantom) {
        console.log("Phantom Wallet được phát hiện!");

        // Kiểm tra trạng thái đăng xuất từ localStorage
        const isLoggedOut = localStorage.getItem('isLoggedOut');

        if (!isLoggedOut) {
            // Tự động kiểm tra nếu đã kết nối trước đó (chỉ nếu người dùng cho phép)
            try {
                const { publicKey } = await window.solana.connect({ onlyIfTrusted: true });
                if (publicKey) {
                    connectedAccount = publicKey.toString();
                    updateWalletInfo(connectedAccount);
                    connectWalletButton.style.display = 'none';
                    logoutWalletButton.style.display = 'block';
                }
            } catch (error) {
                console.error("Lỗi khi tự động kết nối:", error);
            }
        }
    } else {
        walletInfoText.innerHTML = 'Không tìm thấy ví Phantom. Vui lòng <a href="https://phantom.app/">cài đặt Phantom</a>.';
        return;  // Không có ví Phantom
    }

    // Sự kiện kết nối ví
    connectWalletButton.addEventListener('click', async () => {
        if (window.solana && window.solana.isPhantom) {
            try {
                const response = await window.solana.connect();
                connectedAccount = response.publicKey.toString();
                updateWalletInfo(connectedAccount);
                connectWalletButton.style.display = 'none';
                logoutWalletButton.style.display = 'block';
                localStorage.removeItem('isLoggedOut'); // Xóa trạng thái đăng xuất
            } catch (error) {
                console.error('Error connecting to wallet:', error);
                walletInfoText.textContent = 'Lỗi khi kết nối ví. Vui lòng thử lại.';
            }
        }
    });

    // Sự kiện đăng xuất ví
    logoutWalletButton.addEventListener('click', () => {
        connectedAccount = null;
        walletInfoText.innerHTML = 'Chưa kết nối ví';
        connectWalletButton.style.display = 'block';
        logoutWalletButton.style.display = 'none';
        localStorage.setItem('isLoggedOut', 'true'); // Lưu trạng thái đăng xuất
    });

    // Hàm cập nhật thông tin ví và số dư trên Solana Devnet
    async function updateWalletInfo(account) {
        try {
            const connection = new solanaWeb3.Connection(solanaWeb3.clusterApiUrl('devnet'));
            const balance = await connection.getBalance(new solanaWeb3.PublicKey(account));
            const balanceInSol = balance / solanaWeb3.LAMPORTS_PER_SOL;
            walletInfoText.innerHTML = `
                <strong>Đã kết nối với ví:</strong> ${account}<br>
                <strong>Số dư (SOL):</strong> ${balanceInSol} SOL<br>
                <strong>Mạng hiện tại:</strong> Solana Devnet
            `;
        } catch (error) {
            console.error("Lỗi khi lấy thông tin số dư:", error);
            walletInfoText.textContent = 'Lỗi khi lấy số dư. Vui lòng thử lại.';
        }
    }
});
